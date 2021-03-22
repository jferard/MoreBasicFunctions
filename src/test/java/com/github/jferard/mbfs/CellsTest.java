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

public class CellsTest {
    public Cells cells;

    @Before
    public void setUp() {
        cells = new Cells(null);
    }

    @Test
    public void testAlphabetic() {
        Assert.assertEquals("A", cells.alphabeticCol(0));
        Assert.assertEquals("Z", cells.alphabeticCol(25));
        Assert.assertEquals("AA", cells.alphabeticCol(26));
        Assert.assertEquals("AZ", cells.alphabeticCol(51));
        Assert.assertEquals("BA", cells.alphabeticCol(52));
        Assert.assertEquals("AMJ", cells.alphabeticCol(1023));
    }

    @Test
    public void testNumeric() {
        Assert.assertEquals(cells.numericCol("A"), 0);
        Assert.assertEquals(cells.numericCol("Z"), 25);
        Assert.assertEquals(cells.numericCol("AA"), 26);
        Assert.assertEquals(cells.numericCol("AZ"), 51);
        Assert.assertEquals(cells.numericCol("BA"), 52);
        Assert.assertEquals(cells.numericCol("AMJ"), 1023);
    }

    public void test() {
        for (int i = 0; i < 10000; i += 10) {
            String col = cells.alphabeticCol(i);
            Assert.assertEquals(i, cells.numericCol(col));
        }
    }
}