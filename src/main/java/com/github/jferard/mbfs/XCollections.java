// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XCollections extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("avg", 0, 64), new MethodTypeInfo("max", 1, 64), new MethodTypeInfo("min", 2, 64), new MethodTypeInfo("shuffle", 3, 64), new MethodTypeInfo("sort", 4, 64), new MethodTypeInfo("toArray", 5, 64) };
    
    Object avg(final Object p0);
    
    Object max(final Object p0);
    
    Object min(final Object p0);
    
    Object[] shuffle(final Object p0);
    
    Object[] sort(final Object p0);
    
    Object[] toArray(final Object p0);
}
