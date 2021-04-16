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

import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

public class Cells extends WeakBase
        implements XServiceInfo, XCells {
    public static final String implementationName = com.github.jferard.mbfs.Cells.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Cells"};
    public static final int MAX_LETTERS = 3;
    public static final int MAX_COLUMN_NUMBER;
    public static final int A = 'A';

    static {
        int m = 1;
        for (int i = 0; i < MAX_LETTERS; i++) {
            m = m * 26 + 1;
        }
        // 26**3 + 26**2 + 26 - 1
        MAX_COLUMN_NUMBER = m - 2;
    }

    private final XComponentContext xContext;

    public Cells(XComponentContext context) {
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
    public String alphabeticCol(int c) {
        if (c < 0 || c > MAX_COLUMN_NUMBER) {
            throw new RuntimeException("Bad col number: " + c);
        }
        char[] cs = new char[MAX_LETTERS];
        int cur = c;
        int i = MAX_LETTERS;
        while (cur >= 0 && i > 0) {
            cs[i - 1] = (char) (A + cur % 26);
            i -= 1;
            cur = cur / 26 - 1;
        }
        return new String(cs, i, MAX_LETTERS - i);
    }

    @Override
    public int numericCol(String col) {
        if (col.isEmpty() || col.length() > MAX_LETTERS) {
            throw new RuntimeException("Bad column representation: " + col);
        }
        int ret = (int) col.charAt(0) - A;
        for (int i = 1; i < col.length(); i++) {
            ret = (ret + 1) * 26 + (int) col.charAt(i) - A;
        }
        return ret;
    }
}
