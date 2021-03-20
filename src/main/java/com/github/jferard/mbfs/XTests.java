// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.uno.RuntimeException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XTests extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("assertEquals", 0, 0), new MethodTypeInfo("assertNotEquals", 1, 0), new MethodTypeInfo("assertTrue", 2, 0), new MethodTypeInfo("errors", 3, 0), new MethodTypeInfo("message", 4, 0), new MethodTypeInfo("check", 5, 0) };
    
    void assertEquals(final Object p0, final Object p1, final String p2);
    
    void assertNotEquals(final Object p0, final Object p1, final String p2);
    
    void assertTrue(final boolean p0, final String p1);
    
    int errors();
    
    String message();
    
    void check() throws RuntimeException;
}
