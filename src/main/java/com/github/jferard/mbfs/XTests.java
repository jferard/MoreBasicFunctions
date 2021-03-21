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
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("assertEquals", 0, 0), new MethodTypeInfo("assertArrayEquals", 1, 0), new MethodTypeInfo("assertNotEquals", 2, 0), new MethodTypeInfo("assertArrayNotEquals", 3, 0), new MethodTypeInfo("assertTrue", 4, 0), new MethodTypeInfo("assertFalse", 5, 0), new MethodTypeInfo("errors", 6, 0), new MethodTypeInfo("message", 7, 0), new MethodTypeInfo("check", 8, 0) };
    
    void assertEquals(final String p0, final Object p1, final Object p2);
    
    void assertArrayEquals(final String p0, final Object[] p1, final Object[] p2);
    
    void assertNotEquals(final String p0, final Object p1, final Object p2);
    
    void assertArrayNotEquals(final String p0, final Object[] p1, final Object[] p2);
    
    void assertTrue(final String p0, final boolean p1);
    
    void assertFalse(final String p0, final boolean p1);
    
    int errors();
    
    String message();
    
    void check() throws RuntimeException;
}
