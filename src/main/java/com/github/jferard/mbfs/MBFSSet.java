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

import com.sun.star.container.ElementExistException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.container.XSet;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MBFSSet extends WeakBase
        implements XServiceInfo, XMBFSSet, XInitialization {
    public static final String implementationName = MBFSSet.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Set"};

    public static XSet create(XComponentContext context, final Type elementType) {
        return createEmpty(context, elementType);
    }

    public static XSet fromArray(XComponentContext context, final Type elementType,
                                  final Object[] elements) {
        MBFSSet ret = MBFSSet.createEmpty(context, elementType);
        ret.insertArray(elements);
        return ret;
    }

    public static XSet fromEnumerable(XComponentContext context, final Type elementType,
                                       final XEnumerationAccess enumerable)
            throws WrappedTargetException, NoSuchElementException {
        XEnumeration enumeration = enumerable.createEnumeration();
        return fromEnumeration(context, elementType, enumeration);
    }

    public static XSet fromEnumeration(XComponentContext context, final Type elementType,
                                        final XEnumeration enumeration)
            throws WrappedTargetException, NoSuchElementException {
        MBFSSet ret = MBFSSet.createEmpty(context, elementType);
        ret.insertEnumeration(enumeration);
        return ret;
    }

    public static XSet fromIndexed(XComponentContext context, final Type elementType,
                                    XIndexAccess indexed)
            throws IndexOutOfBoundsException, WrappedTargetException {
        MBFSSet ret = MBFSSet.createEmpty(context, elementType);
        ret.insertIndexed(indexed);
        return ret;
    }

    private static MBFSSet createEmpty(XComponentContext context, final Type elementType) {
        MBFSSet ret = new MBFSSet(context);
        ret.initializeType(elementType);
        ret.initializeEmptySet();
        return ret;
    }

    private final XComponentContext xContext;
    private Class<?> zClass;
    private Type elementType;
    private int size;
    private Set<Object> set;

    public MBFSSet(XComponentContext context) {
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
    public boolean has(Object o) {
        return false;
    }

    @Override
    public void insert(Object o) throws IllegalArgumentException, ElementExistException {
        o = this.cast(o);
        if (this.set.contains(o)) {
            throw new ElementExistException(o.toString());
        }
        this.set.add(o);
    }

    @Override
    public void remove(Object o) throws IllegalArgumentException, NoSuchElementException {
        if (!this.set.remove(o)) {
            throw new NoSuchElementException(o.toString());
        }
    }

    private Object cast(Object o) throws IllegalArgumentException {
        if (this.zClass.isInstance(o)) {
            return o;
        } else if (this.zClass == int.class) {
            if (o instanceof Byte) {
                return ((Byte) o).intValue();
            } else if (o instanceof Short) {
                return ((Short) o).intValue();
            }
        }
        throw new IllegalArgumentException(
                "Expected a `" + this.zClass + "` but got a `" + o.getClass() + "` (`" + o +
                        "`)");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object take() throws NoSuchElementException {
        Iterator<Object> iterator = this.set.iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException();
        }
        Object ret = iterator.next();
        this.set.remove(ret);
        return ret;
    }

    @Override
    public Object[] toArray() {
        return this.set.toArray();
    }

    @Override
    public XEnumeration createEnumeration() {
        return new XEnumeration() {
            final Iterator<Object> it = MBFSSet.this.set.iterator();

            @Override
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override
            public Object nextElement() {
                return it.next();
            }
        };
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
        this.initializeEmptySet();
        if (objects.length == 0) {
            this.initializeType(Type.ANY);
        } else if (objects.length == 1) {
            this.initializeType((Type) objects[0]);
        } else {
            this.initializeType((Type) objects[0]);
            insertCollection(objects[1]);
        }
    }

    private void initializeType(Type type) {
        this.elementType = type;
        this.zClass = type.getZClass();
    }

    private void initializeEmptySet() {
        this.set = new HashSet<Object>(10);
        this.size = 0;
    }

    @Override
    public void insertCollection(Object collection)
            throws IndexOutOfBoundsException, WrappedTargetException, NoSuchElementException {
        if (collection instanceof Object[]) {
            insertArray((Object[]) collection);
            return;
        }

        XIndexAccess indexed = UnoRuntime.queryInterface(XIndexAccess.class, collection);
        if (indexed != null) {
            insertIndexed(indexed);
            return;
        }

        XEnumerationAccess enumerable =
                UnoRuntime.queryInterface(XEnumerationAccess.class, collection);
        if (enumerable != null) {
            XEnumeration enumeration = enumerable.createEnumeration();
            insertEnumeration(enumeration);
            return;
        }

        XEnumeration enumeration = UnoRuntime.queryInterface(XEnumeration.class, collection);
        if (enumeration != null) {
            insertEnumeration(enumeration);
            return;
        }
        throw new RuntimeException("Wrong parameter type: " + collection);
    }

    private void insertArray(Object[] array) {
        for (Object o : array) {
            this.safeInsert(o);
        }
    }

    private void safeInsert(Object o) {
        this.set.add(o);
    }

    private void insertIndexed(XIndexAccess indexed)
            throws IndexOutOfBoundsException, WrappedTargetException {
        for (int i = 0; i < indexed.getCount(); i++) {
            this.safeInsert(indexed.getByIndex(i));
        }
    }

    private void insertEnumeration(XEnumeration enumeration)
            throws NoSuchElementException, WrappedTargetException {
        while (enumeration.hasMoreElements()) {
            this.safeInsert(enumeration.nextElement());
        }
    }
}
