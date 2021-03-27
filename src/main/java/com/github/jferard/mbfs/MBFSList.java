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
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import java.util.Arrays;

public class MBFSList extends WeakBase
        implements XServiceInfo, XList, XInitialization {
    public static final String implementationName = MBFSList.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.List"};

    public static XList create(XComponentContext context, final Type elementType) {
        return createEmpty(context, elementType);
    }

    public static XList fromArray(XComponentContext context, final Type elementType,
                                  final Object[] elements) {
        MBFSList ret = MBFSList.createEmpty(context, elementType);
        ret.appendArray(elements);
        return ret;
    }

    public static XList fromEnumerable(XComponentContext context, final Type elementType,
                                       final XEnumerationAccess enumerable)
            throws WrappedTargetException, NoSuchElementException {
        XEnumeration enumeration = enumerable.createEnumeration();
        return fromEnumeration(context, elementType, enumeration);
    }

    public static XList fromEnumeration(XComponentContext context, final Type elementType,
                                        final XEnumeration enumeration)
            throws WrappedTargetException, NoSuchElementException {
        MBFSList ret = MBFSList.createEmpty(context, elementType);
        ret.appendEnumeration(enumeration);
        return ret;
    }

    public static XList fromIndexed(XComponentContext context, final Type elementType,
                                    XIndexAccess indexed)
            throws IndexOutOfBoundsException, WrappedTargetException {
        MBFSList ret = MBFSList.createEmpty(context, elementType);
        ret.appendIndexed(indexed);
        return ret;
    }

    private static MBFSList createEmpty(XComponentContext context, final Type elementType) {
        MBFSList ret = new MBFSList(context);
        ret.initializeType(elementType);
        ret.initializeEmptyArray();
        return ret;
    }

    private final XComponentContext xContext;
    private Class<?> zClass;
    private Type elementType;
    private Object[] array;
    private int size;

    public MBFSList(XComponentContext context) {
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
    public void append(Object o) throws IllegalArgumentException {
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

    private void checkType(Object o) throws IllegalArgumentException {
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
        throw new IllegalArgumentException(
                "Expected a `" + this.zClass + "` but got a `" + o.getClass() + "` + (`" + o +
                        "`)");
    }

    @Override
    public void insert(int pos, Object o)
            throws IllegalArgumentException, IndexOutOfBoundsException {
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
    public Object get(int pos) throws IndexOutOfBoundsException {
        pos = sanitizePos(pos);
        return this.array[pos];
    }

    private int sanitizePos(int pos) throws IndexOutOfBoundsException {
        if (pos < 0) {
            pos += this.size;
        }
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        return pos;
    }

    @Override
    public void set(int pos, Object o) throws IllegalArgumentException, IndexOutOfBoundsException {
        this.checkType(o);
        pos = sanitizePos(pos);
        this.array[pos] = o;
    }

    @Override
    public Object removeLast() {
        if (this.size == 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        this.size--;
        return this.array[this.size];
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[this.size];
        System.arraycopy(this.array, 0, ret, 0, this.size);
        return ret;
    }

    @Override
    public XEnumeration createEnumeration() {
        return new XEnumeration() {
            int i = 0;

            @Override
            public boolean hasMoreElements() {
                return i < MBFSList.this.size;
            }

            @Override
            public Object nextElement() throws NoSuchElementException, WrappedTargetException {
                Object ret = MBFSList.this.array[i];
                i++;
                return ret;
            }
        };
    }

    @Override
    public void replaceByIndex(int pos, Object o)
            throws IllegalArgumentException, IndexOutOfBoundsException, WrappedTargetException {
        this.set(pos, o);
    }

    @Override
    public int getCount() {
        return this.size();
    }

    @Override
    public Object getByIndex(int pos) throws IndexOutOfBoundsException, WrappedTargetException {
        return this.get(pos);
    }

    @Override
    public Type getElementType() {
        return this.elementType;
    }

    @Override
    public boolean hasElements() {
        return this.size() > 0;
    }

    @Override
    public void initialize(Object[] objects) throws Exception {
        if (objects.length > 2) {
            throw new RuntimeException("Too many parameters: " + Arrays.asList(objects));
        }
        this.initializeEmptyArray();
        if (objects.length == 0) {
            this.initializeType(Type.ANY);
        } else if (objects.length == 1) {
            this.initializeType((Type) objects[0]);
        } else {
            this.initializeType((Type) objects[0]);
            appendCollection(objects[1]);
        }
    }

    private void initializeType(Type type) {
        this.elementType = type;
        this.zClass = type.getZClass();
    }

    private void initializeEmptyArray() {
        this.array = new Object[10];
        this.size = 0;
    }

    @Override
    public void appendCollection(Object collection)
            throws IndexOutOfBoundsException, WrappedTargetException, NoSuchElementException {
        if (collection instanceof Object[]) {
            appendArray((Object[]) collection);
            return;
        }

        XIndexAccess indexed = UnoRuntime.queryInterface(XIndexAccess.class, collection);
        if (indexed != null) {
            appendIndexed(indexed);
            return;
        }

        XEnumerationAccess enumerable = UnoRuntime.queryInterface(XEnumerationAccess.class, collection);
        if (enumerable != null) {
            XEnumeration enumeration = enumerable.createEnumeration();
            appendEnumeration(enumeration);
            return;
        }

        XEnumeration enumeration = UnoRuntime.queryInterface(XEnumeration.class, collection);
        if (enumeration != null) {
            appendEnumeration(enumeration);
            return;
        }
        throw new RuntimeException("Wrong parameter type: " + collection);
    }

    private void appendArray(Object[] array) {
        for (Object o : array) {
            this.append(o);
        }
    }

    private void appendIndexed(XIndexAccess indexed)
            throws IndexOutOfBoundsException, WrappedTargetException {
        for (int i = 0; i < indexed.getCount(); i++) {
            this.append(indexed.getByIndex(i));
        }
    }

    private void appendEnumeration(XEnumeration enumeration)
            throws NoSuchElementException, WrappedTargetException {
        while (enumeration.hasMoreElements()) {
            this.append(enumeration.nextElement());
        }
    }

}
