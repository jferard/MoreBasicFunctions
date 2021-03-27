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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexes extends WeakBase
        implements XServiceInfo, XRegexes {
    public static final String implementationName = Regexes.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Regexes"};

    private final XComponentContext xContext;

    public Regexes(XComponentContext context) {
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
    public String[] findAll(String s, String pattern) {
        List<String> ret = new ArrayList<String>();
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        int i=0;
        while (matcher.find(i)) {
            i = matcher.end();
            ret.add(matcher.group());
        }
        return ret.toArray(new String[] {});
    }

    @Override
    public String findFirst(String s, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    @Override
    public boolean match(String s, String pattern) {
        return s.matches(pattern);
    }

    @Override
    public String replaceFirst(String s, String pattern, String replacement) {
        return s.replaceFirst(pattern, replacement);
    }

    @Override
    public String replaceAll(String s, String pattern, String replacement) {
        return s.replaceAll(pattern, replacement);
    }

    @Override
    public String[] split(String s, String pattern) {
        return s.split(pattern);
    }
}
