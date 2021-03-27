// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XRegexes extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("findAll", 0, 0), new MethodTypeInfo("findFirst", 1, 0), new MethodTypeInfo("match", 2, 0), new MethodTypeInfo("replaceFirst", 3, 0), new MethodTypeInfo("replaceAll", 4, 0), new MethodTypeInfo("split", 5, 0) };
    
    String[] findAll(final String p0, final String p1);
    
    String findFirst(final String p0, final String p1);
    
    boolean match(final String p0, final String p1);
    
    String replaceFirst(final String p0, final String p1, final String p2);
    
    String replaceAll(final String p0, final String p1, final String p2);
    
    String[] split(final String p0, final String p1);
}
