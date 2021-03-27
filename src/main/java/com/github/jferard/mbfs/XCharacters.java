// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XCharacters extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("isChar", 0, 0), new MethodTypeInfo("isLetter", 1, 0), new MethodTypeInfo("isLowerCase", 2, 0), new MethodTypeInfo("isUpperCase", 3, 0), new MethodTypeInfo("isDigit", 4, 0), new MethodTypeInfo("isPunctuation", 5, 0), new MethodTypeInfo("isSpace", 6, 0), new MethodTypeInfo("chr", 7, 0), new MethodTypeInfo("ord", 8, 0) };
    
    boolean isChar(final String p0);
    
    boolean isLetter(final String p0) throws IllegalArgumentException;
    
    boolean isLowerCase(final String p0) throws IllegalArgumentException;
    
    boolean isUpperCase(final String p0) throws IllegalArgumentException;
    
    boolean isDigit(final String p0) throws IllegalArgumentException;
    
    boolean isPunctuation(final String p0) throws IllegalArgumentException;
    
    boolean isSpace(final String p0) throws IllegalArgumentException;
    
    String chr(final int p0) throws IllegalArgumentException;
    
    int ord(final String p0) throws IllegalArgumentException;
}
