// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.beans.PropertyValue;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XUtils extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("makePV", 0, 0), new MethodTypeInfo("makePVs", 1, 0), new MethodTypeInfo("glob", 2, 0) };
    
    PropertyValue makePV(final String p0, final Object p1);
    
    PropertyValue[] makePVs(final Object[] p0);
    
    boolean glob(final String p0, final String p1);
}
