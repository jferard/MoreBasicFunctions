/*
 * MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
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

import com.sun.star.container.XEnumeration;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

import java.util.Locale;

public final class Strings extends WeakBase
        implements XServiceInfo, XStrings {
    public static final String implementationName = Strings.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Strings"};

    private final XComponentContext xContext;

    public Strings(XComponentContext context) {
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

    public boolean supportsService(String serviceName) {
        for (String name : serviceNames) {
            if (serviceName.equals(name)) {
                return true;
            }
        }
        return false;
    }


    // com.github.jferard.mbfs.XStrings:
    public String reversed(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public int compare(String p0, String p1) {
        return 0;
    }

    @Override
    public int compareIgnoreCase(String p0, String p1) {
        return p0.compareTo(p1);
    }

    @Override
    public boolean contains(String p0, String p1) {
        return p0.contains(p1);
    }

    @Override
    public boolean containsIgnoreCase(String p0, String p1) {
        return p0.toLowerCase(Locale.US).contains(p1.toLowerCase(Locale.US));
    }

    @Override
    public boolean endsWith(String p0, String p1) {
        return p0.endsWith(p1);
    }

    @Override
    public boolean endsWithIgnoreCase(String p0, String p1) {
        return p0.toLowerCase(Locale.US).endsWith(p1.toLowerCase(Locale.US));
    }

    @Override
    public XEnumeration enumerate(final String p0) {
        return new XEnumeration() {
            int i=0;

            @Override
            public boolean hasMoreElements() {
                return i<p0.length();
            }

            @Override
            public Object nextElement() {
                char ret = p0.charAt(i);
                i++;
                return ret;
            }
        };
    }

    @Override
    public String format(String format, String[] args) {
        return String.format(format, args);
    }

    @Override
    public int indexOf(String p0, String p1) {
        return p0.indexOf(p1);
    }

    @Override
    public int indexOfIgnoreCase(String p0, String p1) {
        return p0.toLowerCase(Locale.US).indexOf(p1.toLowerCase(Locale.US));
    }

    @Override
    public String insert(String p0, String p1, int index) {
        return p0.substring(0, index) + p1 + p0.substring(index);
    }

    @Override
    public String join(String[] strings, String delimiter) {
        return null;
    }

    @Override
    public int lastIndexOf(String p0, String p1) {
        return p0.lastIndexOf(p1);
    }

    @Override
    public int lastIndexOfIgnoreCase(String p0, String p1) {
        return p0.toLowerCase(Locale.US).lastIndexOf(p1.toLowerCase(Locale.US));
   }

    @Override
    public String padLeft(String p0, String p1, int p2) {
        return null;
    }

    @Override
    public String padRight(String p0, String p1, int p2) {
        return null;
    }

    @Override
    public String replace(String p0, String p1, String p2) {
        return null;
    }

    @Override
    public String[] split(String p0, String p1) {
        return new String[0];
    }

    @Override
    public boolean startsWith(String p0, String p1) {
        return false;
    }

    @Override
    public boolean startsWithIgnoreCase(String p0, String p1) {
        return false;
    }

}
