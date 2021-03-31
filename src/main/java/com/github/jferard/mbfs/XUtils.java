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
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("glob", 0, 0), new MethodTypeInfo("makePV", 1, 0), new MethodTypeInfo("makePVs", 2, 0), new MethodTypeInfo("parseByte", 3, 0), new MethodTypeInfo("parseDouble", 4, 0), new MethodTypeInfo("parseFloat", 5, 0), new MethodTypeInfo("parseHyper", 6, 0), new MethodTypeInfo("parseLong", 7, 0), new MethodTypeInfo("parseShort", 8, 0) };
    
    boolean glob(final String p0, final String p1);
    
    PropertyValue makePV(final String p0, final Object p1);
    
    PropertyValue[] makePVs(final Object[] p0);
    
    byte parseByte(final String p0);
    
    double parseDouble(final String p0);
    
    float parseFloat(final String p0);
    
    long parseHyper(final String p0);
    
    int parseLong(final String p0);
    
    short parseShort(final String p0);
}
