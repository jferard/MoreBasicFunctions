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

import com.sun.star.beans.IllegalTypeException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.XComponentContext;

import java.util.Arrays;

public class MBFSList extends WeakBase
        implements XServiceInfo, XList, XInitialization {
    public static final String implementationName = MBFSList.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.List"};

    public static XList create(XComponentContext context, final Type elementType) {
        return new MBFSList(context, elementType);
    }

    public static XList createImmutable(XComponentContext context, final Type elementType,
                                        final Object[] elements) {
        return new MBFSList(context, elementType);
    }

    private final XComponentContext xContext;
    private Class<?> zClass;
    private Object[] array;
    private int size;

    public MBFSList(XComponentContext context) {
        this.xContext = context;
        this.zClass = Object.class;
        this.array = new Object[10];
        this.size = 0;
    }

    MBFSList(XComponentContext context, Type elementType) {
        this.xContext = context;
        this.zClass = elementType.getZClass();
        this.array = new Object[10];
        this.size = 0;
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
    public void append(Object o) throws IllegalTypeException {
        this.checkType(o);
        int length = this.array.length;
        if (this.size == length) {
            Object[] newArray = new Object[length * 2 + 1];
            System.arraycopy(this.array, 0, newArray, 0, length);
            this.array = newArray;
        }
        this.array[this.size] = o;
        this.size++;
    }

    private void checkType(Object o) throws IllegalTypeException {
        if (this.zClass.isInstance(o)) {
            return;
        }
        try {
            Class<?> c = (Class<?>) o.getClass().getField("TYPE").get(null);
            if (this.zClass.isAssignableFrom(c)) {
                return;
            }
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException ignored) {
        }
        throw new IllegalTypeException(
                "Expected a `" + this.zClass + "` but got a `" + o.getClass() + "` + (`" + o +
                        "`)");
    }

    @Override
    public void insert(int pos, Object o) throws IllegalTypeException, IndexOutOfBoundsException {
        this.checkType(o);
        this.size++;
        int length = this.array.length;
        if (this.size == length) {
            Object[] newArray = new Object[length * 2 + 1];
            System.arraycopy(this.array, 0, newArray, 0, pos);
            System.arraycopy(this.array, pos, newArray, pos + 1, length - pos - 1);
            this.array = newArray;
        } else {
            System.arraycopy(this.array, pos, this.array, pos + 1, this.size - pos - 1);
        }
        this.array[pos] = o;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object get(int pos) {
        return this.array[pos];
    }

    @Override
    public void set(int pos, Object o) throws IllegalTypeException {
        this.checkType(o);
        this.array[pos] = o;
    }

    @Override
    public Object removeLast() {
        if (this.size == 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        Object ret = this.array[this.size];
        this.size--;
        return ret;
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[this.size];
        System.arraycopy(this.array, 0, ret, 0, this.size);
        return ret;
    }

    @Override
    public void initialize(Object[] objects) throws Exception {
        if (objects.length == 0) {
            this.zClass = Object.class;
        } else if (objects.length == 1) {
            this.zClass = ((Type) objects[0]).getZClass();
        } else if (objects.length == 2) {
            this.zClass = ((Type) objects[0]).getZClass();
            this.array = (Object[]) objects[1];
        } else {
            throw new RuntimeException("Too many parameters: " + Arrays.asList(objects));
        }
    }
}
