// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexReplace;

public interface XList extends XIndexReplace, XEnumerationAccess
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("append", 0, 0), new MethodTypeInfo("insert", 1, 0), new MethodTypeInfo("size", 2, 0), new MethodTypeInfo("get", 3, 64), new MethodTypeInfo("set", 4, 0), new MethodTypeInfo("removeLast", 5, 64), new MethodTypeInfo("appendCollection", 6, 0), new MethodTypeInfo("toArray", 7, 64), new MethodTypeInfo("subListFrom", 8, 0), new MethodTypeInfo("subListTo", 9, 0), new MethodTypeInfo("subList", 10, 0) };
    
    void append(final Object p0) throws IllegalArgumentException;
    
    void insert(final int p0, final Object p1) throws IllegalArgumentException, IndexOutOfBoundsException;
    
    int size();
    
    Object get(final int p0) throws IndexOutOfBoundsException;
    
    void set(final int p0, final Object p1) throws IllegalArgumentException, IndexOutOfBoundsException;
    
    Object removeLast() throws IndexOutOfBoundsException;
    
    void appendCollection(final Object p0) throws IllegalArgumentException, IndexOutOfBoundsException, WrappedTargetException, NoSuchElementException;
    
    Object[] toArray();
    
    XList subListFrom(final int p0) throws IllegalArgumentException;
    
    XList subListTo(final int p0) throws IllegalArgumentException;
    
    XList subList(final int p0, final int p1) throws IllegalArgumentException;
}
