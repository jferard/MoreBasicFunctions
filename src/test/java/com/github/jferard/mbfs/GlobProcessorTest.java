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

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GlobProcessorTest {
    @Test
    public void test1() {
        // https://en.wikipedia.org/w/index.php?title=Glob_(programming)&oldid=1011600515 examples
        Assert.assertEquals("^Law.*$", new GlobProcessor("Law*").process());
        Assert.assertEquals("^.*Law.*$", new GlobProcessor("*Law*").process());
        Assert.assertEquals("^[CB]at$", new GlobProcessor("[CB]at").process());
        Assert.assertEquals("^Letter[0-9]$", new GlobProcessor("Letter[0-9]").process());
        Assert.assertEquals("^[^C]at$", new GlobProcessor("[!C]at").process());
        Assert.assertEquals("^Letter[^3-5]$", new GlobProcessor("Letter[!3-5]").process());
    }

    @Test
    public void test2() {
        // https://www.linuxjournal.com/content/bash-extended-globbing
        Assert.assertEquals("^.*\\.jpg$", new GlobProcessor("*.jpg").process());
        Assert.assertEquals("^.\\.jpg$", new GlobProcessor("?.jpg").process());
        Assert.assertEquals("^[A-Z].*\\.jpg$", new GlobProcessor("[A-Z]*.jpg").process());
        Assert.assertEquals("^(ab|def)+.*(\\.jpg|\\.gif)+$", new GlobProcessor("+(ab|def)*+(.jpg|.gif)").process());
        Assert.assertEquals("^ab(2|3)+\\.jpg$", new GlobProcessor("ab+(2|3).jpg").process());
        Assert.assertEquals("^(?!*\\.jpg|*\\.gif)$", new GlobProcessor("!(*.jpg|*.gif)").process());
        Assert.assertEquals("^(?!+\\(ab|def).*(\\.jpg|\\.gif)+\\)$", new GlobProcessor("!(+(ab|def)*+(.jpg|.gif))").process());
    }
}