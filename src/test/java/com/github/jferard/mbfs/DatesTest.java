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

public class DatesTest {

    private Dates dates;

    @Before
    public void setUp() {
        dates = new Dates(null);
    }

    @Test
    public void dateTimeTest() {
        Assert.assertEquals(44283.5, dates.datetime(2021, 03, 28, 12, 0, 0.0), 0.01);
    }

    @Test
    public void testStrf() {
        Assert.assertEquals("2021-01-24T12:00:00",
                dates.strftime(44220.5, "yyyy-MM-dd'T'HH:mm:ss"));
    }

    @Test
    public void dateTimeTest2() {
        double datetime = dates.datetime(2021, 3, 28, 18, 45, 56);
        Assert.assertEquals("2021-03-28T18:45:56.000", dates.strftime(datetime, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }
}