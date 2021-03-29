/*
 * MoreBasicFunctions - Java - UNO component that provides more basic functions.
 * Copyright (C) 2021 Julien Férard.
 *
 * MoreBasicFunctions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MoreBasicFunctions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.jferard.mbfs;

import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

public class Paths extends WeakBase
        implements XServiceInfo, XPaths {
    public static final String implementationName = Paths.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Paths"};
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private final XComponentContext xContext;

    public Paths(XComponentContext context) {
        xContext = context;
    }

    @Override
    public String getImplementationName() {
        return implementationName;
    }

    @Override
    public String[] getSupportedServiceNames() {
        return serviceNames;
    }

    @Override
    public boolean supportsService(String serviceName) {
        for (String name : serviceNames) {
            if (serviceName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String concat(String path1, String path2) {
        return new File(path1, path2).getPath();
    }

    @Override
    public boolean exists(String path) {
        return new File(path).exists();
    }

    @Override
    public String extension(String path) {
        int i = path.lastIndexOf('.');
        if (i == -1) {
            return "";
        } else {
            return path.substring(i);
        }
    }

    @Override
    public boolean isDir(String path) {
        return new File(path).isDirectory();
    }

    @Override
    public boolean isFile(String path) {
        return new File(path).isFile();
    }

    @Override
    public String join(String[] chunks) {
        return new File(Util.join(chunks, FILE_SEPARATOR)).getPath();
    }

    @Override
    public String[] list(final String path, String wildCard) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(path);
        }
        final Pattern pattern = Pattern.compile(Util.globToRe(wildCard));
        String[] ret = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        return ret;
    }

    @Override
    public String name(String path) {
        return new File(path).getName();
    }

    @Override
    public String parent(String path) {
        return new File(path).getParent();
    }

    @Override
    public String[] recursiveList(String path, String wildCard) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(path);
        }
        List<String> ret = new ArrayList<String>();
        final Pattern pattern = Pattern.compile(Util.globToRe(wildCard));
        Deque<File> stack = new ArrayDeque<File>();
        stack.push(file);
        String[] names;
        while (stack.size() > 0) {
            File cur = stack.pop();
            names = cur.list();
            if (names == null) {
                continue;
            }
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                file = new File(cur, name);
                if (file.isDirectory()) {
                    stack.push(file);
                } else if (pattern.matcher(name).matches()) {
                    ret.add(file.getPath());
                }
            }
        }
        return ret.toArray(new String[] {});
    }

    @Override
    public String[] split(String path) {
        return path.split(FILE_SEPARATOR);
    }

    @Override
    public String stem(String path) {
        int i = path.indexOf('.');
        if (i == -1) {
            return "";
        } else {
            return path.substring(0, i);
        }
    }

    @Override
    public String withExtension(String path, String extension) {
        int i = path.lastIndexOf('.');
        if (i == -1) {
            return path + extension;
        } else {
            return path.substring(0, i) + extension;
        }
    }
}
