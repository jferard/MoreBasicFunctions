// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.container.XEnumeration;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XStrings extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("charAt", 0, 0), new MethodTypeInfo("compare", 1, 0), new MethodTypeInfo("compareIgnoreCase", 2, 0), new MethodTypeInfo("contains", 3, 0), new MethodTypeInfo("containsIgnoreCase", 4, 0), new MethodTypeInfo("eformat", 5, 0), new MethodTypeInfo("endsWith", 6, 0), new MethodTypeInfo("endsWithIgnoreCase", 7, 0), new MethodTypeInfo("enumerate", 8, 0), new MethodTypeInfo("format", 9, 0), new MethodTypeInfo("indexOf", 10, 0), new MethodTypeInfo("indexOfIgnoreCase", 11, 0), new MethodTypeInfo("insert", 12, 0), new MethodTypeInfo("join", 13, 0), new MethodTypeInfo("lastIndexOf", 14, 0), new MethodTypeInfo("lastIndexOfIgnoreCase", 15, 0), new MethodTypeInfo("lower", 16, 0), new MethodTypeInfo("padLeft", 17, 0), new MethodTypeInfo("padRight", 18, 0), new MethodTypeInfo("replace", 19, 0), new MethodTypeInfo("reversed", 20, 0), new MethodTypeInfo("split", 21, 0), new MethodTypeInfo("startsWith", 22, 0), new MethodTypeInfo("startsWithIgnoreCase", 23, 0), new MethodTypeInfo("substring", 24, 0), new MethodTypeInfo("substringFrom", 25, 0), new MethodTypeInfo("substringTo", 26, 0), new MethodTypeInfo("trim", 27, 0), new MethodTypeInfo("trimLeft", 28, 0), new MethodTypeInfo("trimRight", 29, 0), new MethodTypeInfo("trimSpaces", 30, 0), new MethodTypeInfo("trimLeftSpaces", 31, 0), new MethodTypeInfo("trimRightSpaces", 32, 0), new MethodTypeInfo("unescape", 33, 0), new MethodTypeInfo("upper", 34, 0) };
    
    String charAt(final String p0, final int p1);
    
    int compare(final String p0, final String p1);
    
    int compareIgnoreCase(final String p0, final String p1);
    
    boolean contains(final String p0, final String p1);
    
    boolean containsIgnoreCase(final String p0, final String p1);
    
    String eformat(final String p0, final Object[] p1);
    
    boolean endsWith(final String p0, final String p1);
    
    boolean endsWithIgnoreCase(final String p0, final String p1);
    
    XEnumeration enumerate(final String p0);
    
    String format(final String p0, final Object[] p1);
    
    int indexOf(final String p0, final String p1);
    
    int indexOfIgnoreCase(final String p0, final String p1);
    
    String insert(final String p0, final String p1, final int p2);
    
    String join(final String[] p0, final String p1);
    
    int lastIndexOf(final String p0, final String p1);
    
    int lastIndexOfIgnoreCase(final String p0, final String p1);
    
    String lower(final String p0);
    
    String padLeft(final String p0, final String p1, final int p2) throws IllegalArgumentException;
    
    String padRight(final String p0, final String p1, final int p2) throws IllegalArgumentException;
    
    String replace(final String p0, final String p1, final String p2);
    
    String reversed(final String p0);
    
    String[] split(final String p0, final String p1);
    
    boolean startsWith(final String p0, final String p1);
    
    boolean startsWithIgnoreCase(final String p0, final String p1);
    
    String substring(final String p0, final int p1, final int p2);
    
    String substringFrom(final String p0, final int p1);
    
    String substringTo(final String p0, final int p1);
    
    String trim(final String p0, final String p1) throws IllegalArgumentException;
    
    String trimLeft(final String p0, final String p1) throws IllegalArgumentException;
    
    String trimRight(final String p0, final String p1) throws IllegalArgumentException;
    
    String trimSpaces(final String p0);
    
    String trimLeftSpaces(final String p0);
    
    String trimRightSpaces(final String p0);
    
    String unescape(final String p0);
    
    String upper(final String p0);
}
