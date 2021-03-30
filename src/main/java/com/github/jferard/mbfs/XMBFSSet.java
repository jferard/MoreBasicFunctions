// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.container.XSet;

public interface XMBFSSet extends XSet
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("size", 0, 0), new MethodTypeInfo("take", 1, 64), new MethodTypeInfo("insertCollection", 2, 0), new MethodTypeInfo("toArray", 3, 64) };
    
    int size();
    
    Object take() throws NoSuchElementException;
    
    void insertCollection(final Object p0) throws IllegalArgumentException, IndexOutOfBoundsException, WrappedTargetException, NoSuchElementException;
    
    Object[] toArray();
}
