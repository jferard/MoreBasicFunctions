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

import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.registry.XRegistryKey;

public class MoreBasicFunctions {
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
}
