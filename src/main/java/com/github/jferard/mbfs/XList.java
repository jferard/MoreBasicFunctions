// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.beans.IllegalTypeException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XList extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("append", 0, 0), new MethodTypeInfo("insert", 1, 0), new MethodTypeInfo("size", 2, 0), new MethodTypeInfo("get", 3, 64), new MethodTypeInfo("set", 4, 0), new MethodTypeInfo("removeLast", 5, 64), new MethodTypeInfo("toArray", 6, 64) };
    
    void append(final Object p0) throws IllegalTypeException;
    
    void insert(final int p0, final Object p1) throws IllegalTypeException, IndexOutOfBoundsException;
    
    int size();
    
    Object get(final int p0) throws IndexOutOfBoundsException;
    
    void set(final int p0, final Object p1) throws IllegalTypeException, IndexOutOfBoundsException;
    
    Object removeLast() throws IndexOutOfBoundsException;
    
    Object[] toArray();
}
