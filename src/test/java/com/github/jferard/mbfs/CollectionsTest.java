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

import static org.junit.Assert.*;

public class CollectionsTest {
    private Collections collections;

    @Before
    public void setUp() {
        this.collections = new Collections(null);
    }

    @Test
    public void testShortByte() {
        Assert.assertTrue(this.collections.contains(new short[] {3, 17, 9}, (byte) 9));
    }

    @Test
    public void test1() {
        Assert.assertTrue(this.collections.contains(new Object[] {(double) 524288, 1}, (byte) 1));
    }
}