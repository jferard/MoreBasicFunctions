/*
 * MoreBasicFunctions - A LibreOffice Add-On written in Java that provides more basic functions.
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
import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.registry.XRegistryKey;
import com.sun.star.uno.XComponentContext;

public final class StringsImpl extends WeakBase
        implements XServiceInfo, XStrings {
    public static XSingleComponentFactory __getComponentFactory(String implementation) {
        if (implementation.equals(StringsImpl.implementationName)) {
            return Factory.createComponentFactory(StringsImpl.class, StringsImpl.implementationName,
                    StringsImpl.serviceNames);
        } else {
            return null;
        }
    }

    public static boolean __writeRegistryServiceInfo(XRegistryKey xRegistryKey) {
        return Factory.writeRegistryServiceInfo(StringsImpl.implementationName,
                StringsImpl.serviceNames, xRegistryKey);
    }

    public static final String implementationName = StringsImpl.class.getName();

    public static final String[] serviceNames = {"com.github.jferard.mbfs.Strings"};

    private final XComponentContext xContext;

    public StringsImpl(XComponentContext context) {
        xContext = context;
    }

    public String getImplementationName() {
        return implementationName;
    }

    public String[] getSupportedServiceNames() {
        return serviceNames;
    }

    public boolean supportsService(String serviceName) {
        for (String name : serviceNames) {
            if (serviceName.equals(name)) {
                return true;
            }
        }
        return false;
    }


    // com.github.jferard.mbfs.XStrings:
    public String reversed(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}
