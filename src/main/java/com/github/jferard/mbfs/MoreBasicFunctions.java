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
import com.sun.star.registry.XRegistryKey;

public class MoreBasicFunctions {
    public static final String IMPLEMENTATION_NAME = "implementationName";
    public static final String SERVICE_NAMES = "serviceNames";
    private static final Class<? extends XServiceInfo>[] implementationClasses =
            new Class[]{StringsImpl.class};

    public static XSingleComponentFactory __getComponentFactory(String implementation) {
        for (Class<? extends XServiceInfo> implementationClass : implementationClasses) {
            try {
                String implementationName = getImplementationName(implementationClass);
                if (implementation.equals(implementationName)) {
                    String[] serviceNames = getServiceNames(implementationClass);
                    return Factory.createComponentFactory(
                            implementationClass, implementationName, serviceNames);
                }
            } catch (NoSuchFieldException e) {
                // pass
            } catch (IllegalAccessException e) {
                // pass
            }
        }
        return null;
    }

    public static boolean __writeRegistryServiceInfo(XRegistryKey xRegistryKey) {
        for (Class<? extends XServiceInfo> implementationClass : implementationClasses) {
            try {
                String implementationName = getImplementationName(implementationClass);
                String[] serviceNames = getServiceNames(implementationClass);
                if (!Factory.writeRegistryServiceInfo(
                        implementationName, serviceNames, xRegistryKey)) {
                    return false;
                }
            } catch (NoSuchFieldException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
        }
        return true;
    }

    private static String getImplementationName(Class<? extends XServiceInfo> implementationClass)
            throws IllegalAccessException, NoSuchFieldException {
        return getStaticField(implementationClass, IMPLEMENTATION_NAME, String.class);
    }

    private static String[] getServiceNames(Class<? extends XServiceInfo> implementationClass)
            throws IllegalAccessException, NoSuchFieldException {
        return getStaticField(implementationClass, SERVICE_NAMES, String[].class);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getStaticField(Class<? extends XServiceInfo> implementationClass,
                                        String fieldName, Class<T> _clazz)
            throws IllegalAccessException, NoSuchFieldException {
        return (T) implementationClass.getDeclaredField(fieldName)
                .get(null);
    }
}
