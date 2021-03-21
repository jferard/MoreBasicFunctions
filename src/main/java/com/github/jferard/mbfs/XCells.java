// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XCells extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("alphabeticCol", 0, 0), new MethodTypeInfo("numericCol", 1, 0) };
    
    String alphabeticCol(final int p0);
    
    int numericCol(final String p0);
}
