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

public class Util {
    public static char oneChar(String oneCharString) throws IllegalArgumentException {
        if (oneCharString.length() != 1) {
            throw new IllegalArgumentException("Expected one char but got `" + oneCharString + "`");
        }
        return oneCharString.charAt(0);
    }

    public static Object[] toObjectArray(Object o) {
        if (o instanceof byte[]) {
            byte[] bytes = (byte[]) o;
            Object[] ret = new Byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                ret[i] = bytes[i];
            }
            return ret;
        } else if (o instanceof short[]) {
            short[] shorts = (short[]) o;
            Object[] ret = new Short[shorts.length];
            for (int i = 0; i < shorts.length; i++) {
                ret[i] = shorts[i];
            }
            return ret;
        } else if (o instanceof int[]) {
            int[] ints = (int[]) o;
            Object[] ret = new Integer[ints.length];
            for (int i = 0; i < ints.length; i++) {
                ret[i] = ints[i];
            }
            return ret;
        } else if (o instanceof boolean[]) {
            boolean[] booleans = (boolean[]) o;
            Object[] ret = new Boolean[booleans.length];
            for (int i = 0; i < booleans.length; i++) {
                ret[i] = booleans[i];
            }
            return ret;
        }
        return null;
    }

    public static Object toObject(Class<?> expectedClass, Object o) {
        if (expectedClass == Integer.class) {
            if (o instanceof Byte) {
                return ((Byte) o).intValue();
            } else if (o instanceof Short) {
                return ((Short) o).intValue();
            }
        }
        return null;
    }

    private Util() {
    }
}
