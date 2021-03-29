// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XCollections extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("avg", 0, 64), new MethodTypeInfo("concat", 1, 64), new MethodTypeInfo("concatArrays", 2, 64), new MethodTypeInfo("contains", 3, 0), new MethodTypeInfo("flatten", 4, 64), new MethodTypeInfo("flattenArrays", 5, 64), new MethodTypeInfo("max", 6, 64), new MethodTypeInfo("min", 7, 64), new MethodTypeInfo("randomElement", 8, 64), new MethodTypeInfo("shuffle", 9, 64), new MethodTypeInfo("sort", 10, 64), new MethodTypeInfo("slice", 11, 64), new MethodTypeInfo("times", 12, 64), new MethodTypeInfo("toArray", 13, 64) };
    
    Object avg(final Object p0);
    
    Object[] concat(final Object p0, final Object p1);
    
    Object[] concatArrays(final Object[] p0, final Object[] p1);
    
    boolean contains(final Object p0, final Object p1);
    
    Object[] flatten(final Object[] p0);
    
    Object[] flattenArrays(final Object[][] p0);
    
    Object max(final Object p0);
    
    Object min(final Object p0);
    
    Object randomElement(final Object p0);
    
    Object[] shuffle(final Object p0);
    
    Object[] sort(final Object p0);
    
    Object[][] slice(final Object p0, final int p1);
    
    Object[] times(final Object p0, final int p1);
    
    Object[] toArray(final Object p0);
}
