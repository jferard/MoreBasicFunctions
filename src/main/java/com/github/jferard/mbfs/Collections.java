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

import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Collections extends WeakBase
        implements XServiceInfo, XCollections {
    public static final String implementationName = Collections.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Collections"};
    private final XComponentContext xContext;

    public Collections(XComponentContext context) {
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
    public Object[] sort(Object collection) {
        List<Comparable<Object>> list = this.toList(collection);
        java.util.Collections.sort(list);
        return list.toArray();
    }

    @Override
    public Object[] toArray(Object collection) {
        if (collection instanceof Object[]) {
            return (Object[]) collection;
        }
        Object[] array = Util.toObjectArray(collection);
        if (array != null) {
            return array;
        }
        final XIndexAccess indexed = UnoRuntime.queryInterface(XIndexAccess.class, collection);
        if (indexed != null) {
            array = new Object[indexed.getCount()];
            try {
                for (int i = 0; i < indexed.getCount(); i++) {
                    array[i] = indexed.getByIndex(i);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new java.lang.IndexOutOfBoundsException(e.getMessage());
            } catch (WrappedTargetException e) {
                throw new RuntimeException(e);
            }
            return array;
        }
        final XEnumerationAccess
                enumerable = UnoRuntime.queryInterface(XEnumerationAccess.class, collection);
        final XEnumeration enumeration;
        if (enumerable != null) {
            enumeration = enumerable.createEnumeration();
        } else {
            enumeration = UnoRuntime.queryInterface(XEnumeration.class, collection);
        }
        if (enumeration != null) {
            List<Object> list = new ArrayList<Object>();
            try {
                while (enumeration.hasMoreElements()) {
                    list.add(enumeration.nextElement());
                }
            } catch (NoSuchElementException e) {
                throw new java.lang.IndexOutOfBoundsException(e.getMessage());
            } catch (WrappedTargetException e) {
                throw new RuntimeException(e);
            }
            return list.toArray();
        }
        throw new RuntimeException("Wrong parameter type: " + collection);
    }

    private <T> List<T> toList(Object collection) {
        return new ArrayList<T>(Arrays.asList((T[]) this.toArray(collection)));
    }

    @Override
    public Object[] shuffle(Object collection) {
        Object[] array = this.toArray(collection);
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int j = i + random.nextInt(array.length - i);
            Object temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        return array;
    }

    @Override
    public Object max(Object collection) {
        return java.util.Collections.max(this.<Comparable<Object>>toList(collection));
    }

    @Override
    public Object min(Object collection) {
        return java.util.Collections.min(this.<Comparable<Object>>toList(collection));
    }

    @Override
    public Object avg(Object collection) {
        List<Number> list = this.toList(collection);
        double sum = list.get(0).doubleValue();
        for (int i=1; i<list.size(); i++) {
            sum += list.get(1).doubleValue();
        }
        return sum / list.size();
    }
}
