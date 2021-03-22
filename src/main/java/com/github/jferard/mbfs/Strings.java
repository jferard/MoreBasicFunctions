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
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.Exception;
import com.sun.star.uno.XComponentContext;

import java.util.Arrays;
import java.util.Locale;

public final class Strings extends WeakBase
        implements XServiceInfo, XStrings, XInitialization {
    public static final String implementationName = Strings.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Strings"};

    public static XStrings create(XComponentContext context) {
        return new Strings(context, Locale.getDefault());
    }

    public static XStrings createWithLocale(XComponentContext context, String localeName) {
        Locale locale = getLocale(localeName);
        return new Strings(context, locale);
    }

    private static Locale getLocale(String localeName) {
        String[] parts = localeName.split("_");
        if (parts.length == 1) {
            return new Locale(parts[0]);
        } else {
            return new Locale(parts[0], parts[1]);
        }
    }

    private final XComponentContext xContext;
    private Locale locale;

    public Strings(XComponentContext context) {
        this.xContext = context;
    }

    Strings(XComponentContext context, Locale locale) {
        this.xContext = context;
        this.locale = locale;
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
    public String reversed(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public String charAt(String s, int pos) {
        return new String(new char[]{s.charAt(pos)});
    }

    @Override
    public int compare(String s0, String s1) {
        return s0.compareTo(s1);
    }

    @Override
    public int compareIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).compareTo(s1.toLowerCase(locale));
    }

    @Override
    public boolean contains(String s0, String s1) {
        return s0.contains(s1);
    }

    @Override
    public boolean containsIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).contains(s1.toLowerCase(locale));
    }

    @Override
    public boolean endsWith(String s0, String s1) {
        return s0.endsWith(s1);
    }

    @Override
    public boolean endsWithIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).endsWith(s1.toLowerCase(locale));
    }

    @Override
    public XEnumeration enumerate(final String s) {
        return new XEnumeration() {
            int i = 0;

            @Override
            public boolean hasMoreElements() {
                return i < s.length();
            }

            @Override
            public Object nextElement() {
                char ret = s.charAt(i);
                i++;
                return ret;
            }
        };
    }

    @Override
    public String format(String format, String[] args) {
        return String.format(format, (Object[]) args);
    }

    @Override
    public int indexOf(String s0, String s1) {
        return s0.indexOf(s1);
    }

    @Override
    public int indexOfIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).indexOf(s1.toLowerCase(locale));
    }

    @Override
    public String insert(String s0, String s1, int index) {
        return s0.substring(0, index) + s1 + s0.substring(index);
    }

    @Override
    public String join(String[] strings, String delimiter) {
        if (strings.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            sb.append(delimiter).append(strings[i]);
        }
        return sb.toString();
    }

    @Override
    public int lastIndexOf(String s0, String s1) {
        return s0.lastIndexOf(s1);
    }

    @Override
    public int lastIndexOfIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).lastIndexOf(s1.toLowerCase(locale));
    }

    @Override
    public String lower(String s) {
        return s.toLowerCase(locale);
    }

    @Override
    public String padLeft(String s, String oneCharString, int n) {
        char c = oneChar(oneCharString);
        if (s.length() >= n) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < n; i++) {
            sb.append(c);
        }
        sb.append(s);
        return sb.toString();
    }

    private char oneChar(String oneCharString) {
        if (oneCharString.length() != 1) {
            throw new RuntimeException("Expected one char but got `" + oneCharString + "`");
        }
        return oneCharString.charAt(0);
    }

    @Override
    public String padRight(String s, String oneCharString, int n) {
        if (s.length() >= n) {
            return s;
        }
        char c = oneChar(oneCharString);
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length(); i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String replace(String s, String target, String replacement) {
        return s.replace(target, replacement);
    }

    @Override
    public String[] split(String s, String regex) {
        return s.split(regex);
    }

    @Override
    public boolean startsWith(String s0, String s1) {
        return s0.startsWith(s1);
    }

    @Override
    public boolean startsWithIgnoreCase(String s0, String s1) {
        return s0.toLowerCase(locale).startsWith(s1.toLowerCase(locale));
    }

    @Override
    public String substring(String s, int from, int to) {
        if (to < 0) {
            return s.substring(from);
        } else {
            return s.substring(from, to);
        }
    }

    @Override
    public String trim(String s, String oneCharString) {
        char c = oneChar(oneCharString);
        int i = 0;
        while (s.charAt(i) == c) {
            i++;
        }
        int j = s.length();
        while (s.charAt(j - 1) == c) {
            j--;
        }
        return s.substring(i, j);
    }

    @Override
    public String trimLeft(String s, String oneCharString) {
        char c = oneChar(oneCharString);
        int i = 0;
        while (s.charAt(i) == c) {
            i++;
        }
        return s.substring(i);
    }

    @Override
    public String trimRight(String s, String oneCharString) {
        char c = oneChar(oneCharString);
        int j = s.length();
        while (s.charAt(j - 1) == c) {
            j--;
        }
        return s.substring(0, j);
    }

    @Override
    public String trimSpaces(String s) {
        return s.trim();
    }

    @Override
    public String trimLeftSpaces(String s) {
        int i = 0;
        while (Character.isSpaceChar(s.charAt(i))) {
            i++;
        }
        return s.substring(i);
    }

    @Override
    public String trimRightSpaces(String s) {
        int j = s.length();
        while (Character.isSpaceChar(s.charAt(j - 1))) {
            j--;
        }
        return s.substring(0, j);
    }

    @Override
    public String upper(String s) {
        return s.toUpperCase(locale);
    }

    @Override
    public void initialize(Object[] objects) throws Exception {
        if (objects.length == 0) {
            this.locale = Locale.getDefault();
        } else if (objects.length == 1) {
            this.locale = getLocale((String) objects[0]);
        } else {
            throw new RuntimeException("Too many parameters: "+ Arrays.asList(objects));
        }
    }
}
