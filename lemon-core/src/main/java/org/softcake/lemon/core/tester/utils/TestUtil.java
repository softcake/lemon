/*
 * Copyright 2017 softcake.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.softcake.lemon.core.tester.utils;

import org.softcake.lemon.core.tester.exeptions.PrivateConstructorTesterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

/**
 * TestUtil Class.
 *
 * @author Rene Neubert @softcake.org
 */
public final class TestUtil {

    private TestUtil() {

        throw new IllegalStateException("No instances!");
    }

    /**
     * Returns the {@code Class} object associated with the bested class with the given short string
     * name.
     *
     * <p>For example, the following code fragment returns the runtime {@code Class} descriptor for
     * the class named {@code java.java.util.ArrayList$SubList}:
     *
     * <blockquote> {@code Class t = Class.forNestedName("SubList", new ArrayList<>())}
     * </blockquote>
     *
     * @param className         the short name of the desired static or non-static nested class.
     * @param enclosingInstance the instance of the enclosing class.
     *
     * @return the {@code Class} object for the class with the specified name.
     */
    public static Class<?> forNestedName(final String className, final Object enclosingInstance) {

        if (className == null || className.trim().isEmpty()) {

            throw new IllegalArgumentException("Parameter [className] must not be null or empty!");

        }
        if (enclosingInstance == null) {

            throw new IllegalArgumentException("Parameter [enclosingInstance] must not be null!");

        }

        final Class<?>[] declaredClasses = enclosingInstance.getClass().getDeclaredClasses();

        final String regex = "[\\w]+(\\$" + className + ")$";
        final Pattern pattern = Pattern.compile(regex);

        Class<?> result = null;

        for (final Class<?> aClass : declaredClasses) {
            while (pattern.matcher(aClass.toString()).find()) {
                result = aClass;
                break;
            }
        }

        return result;
    }

    /**
     * Helper method, that returns an instance for the given {@code Class}
     *
     * @param clazz          the {@code Class} Object
     * @param parameter      the parameters for the given {@code Class} i.e. {@code new
     *                       Object({"Hello World", 1})}
     * @param parameterTypes the parameter types i.e. {@code new Object({String.class, int.class})}
     *
     * @return the instance of {@code Object}
     *
     * @throws PrivateConstructorTesterException if this {@code Constructor} object is enforcing
     *                                           Java language access control and the underlying
     *                                           constructor is inaccessible. if the number of
     *                                           actual and formal parameters differ; if an
     *                                           unwrapping conversion for primitive arguments
     *                                           fails; or if, after possible unwrapping, a
     *                                           parameter value cannot be converted to the
     *                                           corresponding formal parameter type by a method
     *                                           invocation conversion; if this constructor pertains
     *                                           to an enum type. if the class that declares the
     *                                           underlying constructor represents an abstract
     *                                           class. if the underlying constructor throws an
     *                                           exception.
     */
    public static Object getEnclosingInstance(final Class<?> clazz,
                                              final Object[] parameter,
                                              final Class<?>[] parameterTypes) {

        if (clazz == null) {

            throw new IllegalArgumentException("Parameter [clazz] must not be null!");

        }
        try {
            final Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(parameter);

        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                InvocationTargetException | IllegalArgumentException e) {

            throw new PrivateConstructorTesterException(e);

        }

    }
}
