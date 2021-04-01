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

import com.sun.star.beans.PropertyState;
import com.sun.star.beans.PropertyValue;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public class Utils extends WeakBase
        implements XServiceInfo, XUtils {
    public static final String implementationName = Utils.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Utils"};
    private final XComponentContext xContext;
    private Locale locale;

    public Utils(XComponentContext context) {
        this.xContext = context;
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
    public PropertyValue makePV(String name, Object value) {
        return new PropertyValue(name, -1, value, PropertyState.DIRECT_VALUE);
    }

    @Override
    public PropertyValue[] makePVs(Object[] namesAndValues) {
        if (namesAndValues.length % 2 == 1) {
            throw new RuntimeException("Odd number of parameters: "+ Arrays.asList(namesAndValues));
        }
        PropertyValue[] propertyValues = new PropertyValue[namesAndValues.length / 2];
        for (int i=0; i<namesAndValues.length; i+=2) {
            String name = (String) namesAndValues[i];
            Object value = namesAndValues[i+1];
            propertyValues[i / 2] = new PropertyValue(name, -1, value, PropertyState.DIRECT_VALUE);
        }
        return propertyValues;
    }

    @Override
    public byte parseByte(String number) {
        return Byte.parseByte(number);
    }

    @Override
    public double parseDouble(String number) {
        return Double.parseDouble(number);
    }

    @Override
    public float parseFloat(String number) {
        return Float.parseFloat(number);
    }

    @Override
    public long parseHyper(String number) {
        return Long.parseLong(number);
    }

    @Override
    public int parseLong(String number) {
        return Integer.parseInt(number);
    }

    @Override
    public short parseShort(String number) {
        return Short.parseShort(number);
    }

    @Override
    public String debugBinding(Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            StringBuilder sb = new StringBuilder().append("Array of (");
            if (clazz.getComponentType().isPrimitive()) {
                sb.append(clazz.getComponentType().getName());
            } else {
                List<String> representations = new ArrayList<String>();
                Set<Class<?>> classes = new HashSet<Class<?>>();
                Object[] array = (Object[]) o;
                for (Object value : array) {
                    Class<?> elementClazz = value.getClass();
                    if (!classes.contains(elementClazz)) {
                        representations.add(debugBinding(value));
                        classes.add(elementClazz);
                    }
                }
                sb.append(Util.join(representations.toArray(new String[]{}), ", "));
            }
            sb.append(')');
            return sb.toString();
        } else {
            return clazz.getName();
        }
    }

    @Override
    public boolean glob(String string, String pattern) {
        String rePattern = Util.globToRe(pattern);
        return Pattern.compile(rePattern).matcher(string).matches();
    }
}
