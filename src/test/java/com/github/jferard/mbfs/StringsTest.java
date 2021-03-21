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
import org.junit.Before;
import org.junit.Test;

public class StringsTest {
    private Strings strings;

    @Before
    public void setUp() {
        strings = new Strings(null);
    }

    @Test
    public void testPadLeft() {
        Assert.assertEquals("0000012345", strings.padLeft("12345", "0", 10));
    }

    @Test
    public void testTrimRight() {
        Assert.assertEquals("12345  ", strings.trimLeftSpaces("   12345  "));
    }

    @Test
    public void testTrimLeft() {
        Assert.assertEquals("   12345", strings.trimRightSpaces("   12345  "));
    }
}