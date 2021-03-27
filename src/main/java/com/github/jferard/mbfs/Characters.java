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
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

public class Characters extends WeakBase
        implements XServiceInfo, XCharacters {
    public static final String implementationName = Characters.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Characters"};

    private final XComponentContext xContext;

    public Characters(XComponentContext context) {
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
    public boolean isChar(String s) {
        return s.length() == 1;
    }

    @Override
    public boolean isLetter(String s) throws IllegalArgumentException {
        return Character.isLetter(Util.oneChar(s));
    }

    @Override
    public boolean isLowerCase(String s) throws IllegalArgumentException {
        return Character.isLowerCase(Util.oneChar(s));
    }

    @Override
    public boolean isUpperCase(String s) throws IllegalArgumentException {
        return Character.isUpperCase(Util.oneChar(s));
    }

    @Override
    public boolean isDigit(String s) throws IllegalArgumentException {
        return Character.isDigit(Util.oneChar(s));
    }

    @Override
    public boolean isPunctuation(String s) throws IllegalArgumentException {
        int t = Character.getType(Util.oneChar(s));
        return t == Character.CONNECTOR_PUNCTUATION || t == Character.DASH_PUNCTUATION ||
                t == Character.END_PUNCTUATION || t == Character.FINAL_QUOTE_PUNCTUATION ||
                t == Character.INITIAL_QUOTE_PUNCTUATION || t == Character.OTHER_PUNCTUATION ||
                t == Character.START_PUNCTUATION;
    }

    @Override
    public boolean isSpace(String s) throws IllegalArgumentException {
        return Character.isWhitespace(Util.oneChar(s));
    }

    @Override
    public String chr(int codePoint) throws IllegalArgumentException {
        return new String(new char[]{(char) codePoint});
    }

    @Override
    public int ord(String s) throws IllegalArgumentException {
        return Util.oneChar(s);
    }
}
