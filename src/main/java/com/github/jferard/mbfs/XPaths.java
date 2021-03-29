// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XPaths extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("concat", 0, 0), new MethodTypeInfo("exists", 1, 0), new MethodTypeInfo("extension", 2, 0), new MethodTypeInfo("isDir", 3, 0), new MethodTypeInfo("isFile", 4, 0), new MethodTypeInfo("join", 5, 0), new MethodTypeInfo("list", 6, 0), new MethodTypeInfo("name", 7, 0), new MethodTypeInfo("parent", 8, 0), new MethodTypeInfo("recursiveList", 9, 0), new MethodTypeInfo("split", 10, 0), new MethodTypeInfo("stem", 11, 0), new MethodTypeInfo("withExtension", 12, 0) };
    
    String concat(final String p0, final String p1);
    
    boolean exists(final String p0);
    
    String extension(final String p0);
    
    boolean isDir(final String p0);
    
    boolean isFile(final String p0);
    
    String join(final String[] p0);
    
    String[] list(final String p0, final String p1) throws IllegalArgumentException;
    
    String name(final String p0);
    
    String parent(final String p0);
    
    String[] recursiveList(final String p0, final String p1) throws IllegalArgumentException;
    
    String[] split(final String p0);
    
    String stem(final String p0);
    
    String withExtension(final String p0, final String p1);
}
