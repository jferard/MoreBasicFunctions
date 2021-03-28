// 
// Decompiled by Procyon v0.5.32
// 

package com.github.jferard.mbfs;

import com.sun.star.lib.uno.typeinfo.MethodTypeInfo;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lib.uno.typeinfo.TypeInfo;
import com.sun.star.uno.XInterface;

public interface XDates extends XInterface
{
    public static final TypeInfo[] UNOTYPEINFO = { new MethodTypeInfo("date", 0, 0), new MethodTypeInfo("datetime", 1, 0), new MethodTypeInfo("now", 2, 0), new MethodTypeInfo("strftime", 3, 0), new MethodTypeInfo("strptime", 4, 0), new MethodTypeInfo("time", 5, 0), new MethodTypeInfo("timestamp", 6, 0) };
    
    int date(final int p0, final int p1, final int p2);
    
    double datetime(final int p0, final int p1, final int p2, final int p3, final int p4, final double p5);
    
    double now();
    
    String strftime(final double p0, final String p1) throws IllegalArgumentException;
    
    double strptime(final String p0, final String p1) throws IllegalArgumentException;
    
    double time(final int p0, final int p1, final double p2);
    
    long timestamp(final double p0);
}
