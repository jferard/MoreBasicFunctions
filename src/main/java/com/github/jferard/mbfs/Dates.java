/*
 * MoreBasicFunctions - Java - UNO component that provides more basic functions.
 * Copyright (C) 2021 Julien Férard.
 *
 * MoreBasicFunctions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MoreBasicFunctions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.jferard.mbfs;

import com.sun.star.lang.XServiceInfo;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.uno.XComponentContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Dates extends WeakBase
        implements XServiceInfo, XDates {
    public static final double MILLIS_BY_SECOND = 1000.0;
    public static final double MILLIS_BY_DAY = 24 * 60 * 60 * MILLIS_BY_SECOND;
    public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

    private static final long DATE_ZERO_IN_MILLIS;
    static {
        Calendar gmt = Calendar.getInstance(GMT_TIME_ZONE);
        gmt.setTimeInMillis(0);
        gmt.set(1899, Calendar.DECEMBER, 30);
        DATE_ZERO_IN_MILLIS = gmt.getTimeInMillis();
    }

    public static final String implementationName = Dates.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Dates"};

    private final XComponentContext xContext;
    private final Calendar gmt;

    public Dates(XComponentContext context) {
        xContext = context;
        gmt = Calendar.getInstance(GMT_TIME_ZONE);
    }

    @Override
    public String getImplementationName() {
        return implementationName;
    }

    @Override
    public String[] getSupportedServiceNames() {
        return serviceNames;
    }

    @Override
    public boolean supportsService(String serviceName) {
        for (String name : serviceNames) {
            if (serviceName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int date(int year, int month, int day) {
        gmt.setTimeInMillis(0);
        gmt.set(year, month-1, day);
        return (int) millisToDouble(gmt.getTimeInMillis());
    }

    private double millisToDouble(long millis) {
        long deltaInMillis = millis - DATE_ZERO_IN_MILLIS;
        return deltaInMillis / MILLIS_BY_DAY;
    }

    private long doubleToMillis(double time) {
        long deltaInMillis = (long) (time * MILLIS_BY_DAY);
        return deltaInMillis + DATE_ZERO_IN_MILLIS;
    }

    @Override
    public double datetime(int year, int month, int day, int hours, int minutes,
                           double seconds) {
        gmt.setTimeInMillis(0);
        int secs = (int) seconds;
        gmt.set(year, month-1, day, hours, minutes, secs);
        gmt.set(Calendar.MILLISECOND, (int) ((seconds - secs)* MILLIS_BY_SECOND));
        return millisToDouble(gmt.getTimeInMillis());
    }

    @Override
    public double now() {
        return millisToDouble(Calendar.getInstance(GMT_TIME_ZONE).getTimeInMillis());
    }

    @Override
    public String strftime(double time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(GMT_TIME_ZONE);
        Date date = new Date(doubleToMillis(time));
        return dateFormat.format(date);
    }

    @Override
    public double strptime(String str, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(GMT_TIME_ZONE);
        try {
            Date date = dateFormat.parse(str);
            return millisToDouble(date.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public double time(int hours, int minutes, double seconds) {
        long millis = (long) (((hours * 60 + minutes) * 60 + seconds)*MILLIS_BY_SECOND);
        return millisToDouble(millis);
    }

    @Override
    public long timestamp(double time) {
        Date date = new Date(doubleToMillis(time));
        return date.getTime();
    }
}
