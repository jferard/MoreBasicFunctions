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
import com.sun.star.uno.RuntimeException;
import com.sun.star.uno.XComponentContext;

import java.util.ArrayList;
import java.util.List;

public class Tests extends WeakBase
        implements XServiceInfo, XTests {
    public static final String implementationName = Tests.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Tests"};

    private final XComponentContext xContext;
    private final List<String> errors;
    private int count;

    public Tests(XComponentContext context) {
        xContext = context;
        count = 0;
        errors = new ArrayList<String>();
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
    public void assertEquals(Object expected, Object actual, String message) {
        this.count++;
        if (expected == null && actual != null || !(expected.equals(actual))) {
            this.errors.add(String.format("%s: objects `%s` and `%s` are not equal", message, expected, actual));
        }
    }

    @Override
    public void assertNotEquals(Object expected, Object actual, String message) {
        this.count++;
        if (expected == null && actual == null || expected.equals(actual)) {
            this.errors.add(String.format("%s: objects `%s` and `%s` are equal", message, expected, actual));
        }
    }

    @Override
    public void assertTrue(boolean b, String message) {
        this.count++;
        if (!b) {
            this.errors.add(String.format("%s: expected True", message));
        }
    }

    @Override
    public void assertFalse(boolean b, String message) {
        this.count++;
        if (b) {
            this.errors.add(String.format("%s: expected False", message));
        }
    }

    @Override
    public int errors() {
        return errors.size();
    }

    @Override
    public String message() {
        if (errors.size() == 0) {
            return String.format("Success: %d/%d", count, count);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Failure: %d/%d", errors.size(), count));
        for (String error : errors) {
            sb.append("\n").append(error);
        }
        return sb.toString();
    }

    @Override
    public void check() throws RuntimeException {
        if (errors.size() != 0) {
            throw new RuntimeException(this.message());
        }
    }
}
