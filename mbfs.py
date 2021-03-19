#!/usr/bin/python3.8
# coding: utf-8

#  MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
#  Copyright (C) 2021 Julien Férard.
#
#  MoreBasicFunctions is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  MoreBasicFunctions is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program.  If not, see <https://www.gnu.org/licenses/>.

import os
import shutil
import subprocess
import zipfile
from pathlib import Path

import sys

RDB = ".rdb"
TYPES_RDB = "types"+RDB

pt = os.path.join

office_home = os.getenv("OFFICE_HOME", "/usr/lib/libreoffice")
oo_sdk_home = os.getenv("OO_SDK_HOME", pt(office_home, "sdk"))
office_program_path = os.getenv("OFFICE_PROGRAM_PATH", pt(office_home, "program"))

lib_name = "MoreBasicFunctions"
service_names = {"Strings": ["XStrings"]}

lib_module = "com.github.jferard.mbfs"
lib_path = lib_module.replace(".", "/")
lib_root = lib_module.split(".")[0]

work_dir = "temp"
oxt_resources = "oxt-resources"
jar_resources = "jar-resources"
oxt_target = "oxt-target"

mbfs = "."

target_dir = pt(work_dir, "t_target")
src_dir = pt(work_dir, "t_src")

# SDK binaries
idlc = pt(oo_sdk_home, "bin", "idlc")
regmerge = pt(office_program_path, "regmerge")
regview = pt(office_program_path, "regview")
javamaker = pt(oo_sdk_home, "bin", "javamaker")
uno_skeletonmaker = pt(oo_sdk_home, "bin", "uno-skeletonmaker")


class MoreBasicFunctions:
    def __init__(self):
        pass

    def prepare(self):
        self._create_dirs()
        self._build_urds()
        self._merge_urds()
        self.check()
        self._generate_interface()
        self._generate_template()

    def _create_dirs(self):
        Path(work_dir).mkdir(parents=True, exist_ok=True)
        Path(src_dir).mkdir(parents=True, exist_ok=True)
        Path(target_dir).mkdir(parents=True, exist_ok=True)

    def _build_urds(self):
        for path in Path(jar_resources).glob("*.idl"):
            print(f"Processing {path}")
            command = [idlc, "-we", "-I", pt(oo_sdk_home, "idl"), "-O", work_dir,
                       str(path)]
            process = self._run_command(command)

    def _run_command(self, command, **kwargs):
        print("> " + " ".join(command))
        process = subprocess.run(command, stdout=subprocess.PIPE, universal_newlines=True, **kwargs)
        if process.returncode != 0:
            raise Exception(process.returncode)
        return process

    def _merge_urds(self):
        print(f"Merging URDS")
        process = self._run_command(
            [regmerge, "-v", pt(work_dir, lib_name + RDB), "UCR"] + [str(p) for p in
                                                                     Path(work_dir).glob(
                                                                            "*.urd")])

    def check(self):
        print(f"Viewing RDB")
        process = self._run_command(
            [regview, pt(work_dir, lib_name + RDB)])
        print(process.stdout)

    def _generate_interface(self):
        types = [f"{lib_module}.{path.stem}" for path in Path(jar_resources).glob("X*.idl")]
        print(f"Generating interfaces: {types}")
        process = self._run_command(
            [javamaker, "-T", ";".join(types), "-nD", pt(office_program_path, TYPES_RDB),
             pt("..", lib_name + RDB)], cwd=target_dir)

    def _generate_template(self):
        types = [ ]
        print(f"Generating template")
        for path in Path(jar_resources).glob("X*.idl"):
            interface = f"{lib_module}.{path.stem}"
            service = f"{lib_module}.{path.stem[1:]}Impl"
            process = self._run_command(
                [uno_skeletonmaker, "component", "-l",
                 pt(office_program_path, "types.rdb"), "-l", pt("..", lib_name + RDB), "-n",
                 service, "-t", interface], cwd=src_dir)

    def build(self):
        try:
            shutil.rmtree(Path(oxt_target))
        except FileNotFoundError:
            pass
        Path(oxt_target).mkdir(parents=True, exist_ok=True)
        self._maven()
        self._copy_jar()
        self._copy_resources()
        self._create_oxt()

    def _maven(self):
        process = self._run_command(
            ["mvn", "clean", "install"]
        )

    def _copy_jar(self):
        for p in Path("target").glob(lib_name + "*.jar"):
            src = str(p)
            dest = pt(oxt_target, lib_name + ".jar")
            print(f"> Copy jar: {src} -> {dest}")
            shutil.copy(src, dest)

    def _copy_resources(self):
        print(f"> Copy resources: {oxt_resources}")
        shutil.copytree(oxt_resources, oxt_target, dirs_exist_ok=True)

    def _create_oxt(self):
        print(f"> Zip everything: {oxt_resources}")
        dest = zipfile.ZipFile(lib_name + ".oxt", 'w', zipfile.ZIP_DEFLATED)
        for root, dirs, files in os.walk(oxt_target):
            for file in files:
                name = os.path.join(root, file)
                dest.write(name, os.path.relpath(name, oxt_target))


def main():
    mbfs = MoreBasicFunctions()
    # mbfs.prepare() # generate the files
    mbfs.build()


if __name__ == "__main__":
    main()
