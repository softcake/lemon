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

package org.softcake.lemon.core.tester;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.softcake.lemon.core.tester.exeptions.PrivateConstructorTesterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author Rene Neubert.
 */
public final class PrivateConstructorTester {

    private final Class<?> clazz;

    private final Class<? extends Throwable> expectedExceptionType;

    private final String expectedExceptionMessage;

    private final Class<?>[] expectedParameters;

    private final Object outerInstance;

    private PrivateConstructorTester(final Class<?> clazz,
                                     final Class<? extends Throwable> expectedExceptionType,
                                     final String expectedExceptionMessage,
                                     final Object outerInstance,
                                     final Class<?>... expectedParameters) {

        this.clazz = clazz;
        this.expectedExceptionType = expectedExceptionType;
        this.expectedExceptionMessage = expectedExceptionMessage;
        this.expectedParameters = expectedParameters == null || expectedParameters.length == 0
                                  ? null
                                  : Arrays.copyOf(expectedParameters, expectedParameters.length);
        this.outerInstance = outerInstance;
    }

    /**
     * Creates instance of {@link Builder}.
     * <p>
     * For nested or static classes use {@link Builder#forClass(Class, Object)}! </p>
     *
     * @param clazz class that needs to be checked.
     *
     * @return {@link Builder} which will prepare check of the passed class.
     *
     * @throws IllegalArgumentException if clazz null or empty
     */
    public static Builder forClass(final Class<?> clazz) {

        return forClass(clazz, null);
    }

    /**
     * Creates instance of {@link Builder}. <p> Method is also useful if you want to check nested
     * classes. The nested {@link Class} must no be direct accessible. In this case, it is necessary
     * to pass in an instance of the outer class. </p>
     *
     * @param clazz         the class (also nested or static) that needs to be checked
     * @param outerInstance instance of the enclosing class or {@code null} if not necessary
     *
     * @return {@link Builder} which will prepare check of the passed class.
     *
     * @throws IllegalArgumentException if clazz {@code null} or empty
     */
    public static Builder forClass(final Class<?> clazz, final Object outerInstance) {

        if (clazz == null) {

            throw new IllegalArgumentException("Parameter [clazz] must not be null!");

        }

        return new Builder(clazz, outerInstance);
    }

    /**
     * Runs the check which will assert that particular class has one private constructor
     * which throws or not throws exception.
     */
    @SuppressWarnings("all")
    private void check() {

        final Constructor<?>[] constructors = this.clazz.getDeclaredConstructors();
        this.assertThatClassHasOnlyOneConstructor(constructors);

        final Constructor<?> constructor = constructors[0];
        this.assertThatConstructorCorrespondRules(constructor);

        this.assertThatConstructorParametersCorrespondRules(constructor.getParameterTypes());

        if (this.expectedParameters != null) {
            return;

        }

        try {

            constructor.setAccessible(true);

            if (Modifier.isStatic(clazz.getModifiers()) || !isNestedClass()) {
                constructor.newInstance();
            } else {
                constructor.newInstance(this.outerInstance);
            }

        } catch (final InvocationTargetException e) {

            final Throwable cause = e.getCause();

            this.assertThatExceptionCorrespondRules(cause);
            this.assertThatExceptionMessageCorrespondRules(cause);

            if (this.expectedExceptionType == null) {

                throw new IllegalStateException("For " + this.clazz + " no exception was expected",
                                                e);

            }

        } catch (InstantiationException | IllegalAccessException e) {
            throw new PrivateConstructorTesterException(e);

        }
    }

    private boolean isNestedClass() {

        final String className = this.clazz.getName();
        return className.substring(className.lastIndexOf('.') + 1).contains("$")
               && !Modifier.isStatic(this.clazz.getModifiers());
    }

    /**
     * A utility class constructor should only have one private constructor.
     *
     * @param constructors the constructors array
     */
    private void assertThatClassHasOnlyOneConstructor(final Constructor<?>[] constructors) {

        assertThat("Utility " + this.clazz + " should have only one private constructor",
                   constructors.length,
                   is(1));
    }

    /**
     * A utility class constructor should only have one private constructor.
     *
     * @param constructor constructor
     */
    private void assertThatConstructorCorrespondRules(final Constructor<?> constructor)
            throws AssertionError {

        if (constructor.isAccessible() || !Modifier.isPrivate(constructor.getModifiers())) {

            throw new AssertionError("Constructor of " + this.clazz + " must be private");

        }
    }

    /**
     * @param cause the throwable.
     */
    @SuppressWarnings("squid:S1192")
    private void assertThatExceptionMessageCorrespondRules(final Throwable cause) {

        if (this.expectedExceptionMessage != null) {

            assertThat("Mismatch in " + this.clazz + " expected exception message",
                       this.expectedExceptionMessage,
                       is(cause.getMessage()));

        }
    }

    /**
     * @param cause the throwable.
     */
    private void assertThatExceptionCorrespondRules(final Throwable cause) {

        if (this.expectedExceptionType != null) {

            assertThat("Mismatch in " + this.clazz + " expected constructor exception",
                       this.expectedExceptionType,
                       is(equalTo(cause.getClass())));
        }
    }

    /**
     * @param parameterTypes the constructors parameter types.
     */
    private void assertThatConstructorParametersCorrespondRules(final Class<?>[] parameterTypes) {

        Class<?>[] parameterType = parameterTypes;

        if (isNestedClass() && parameterType.length > 0) {

            parameterType = Arrays.stream(parameterTypes)
                                  .filter(e -> !e.equals(parameterTypes[0]))
                                  .toArray(Class<?>[]::new);

        }

        if (parameterType.length > 0) {
            if (expectedParameters == null) {
                throw new AssertionError(this.clazz
                                         + " has non-default constructor with some parameters");
            } else {
                assertThat("Mismatch in " + this.clazz + " constructors parameter(s)",
                           parameterType,
                           is(equalTo(this.expectedParameters)));
            }
        }
    }

    /**
     * @author Rene Neubert.
     */
    @SuppressWarnings("squid:S2972")
    public static final class Builder {

        private final Object outerInstance;

        private final Class<?> clazz;

        private Class<? extends Throwable> expectedExceptionType;

        private String expectedExceptionMessage;

        private Class<?>[] expectedParameters;

        private Builder(final Class<?> clazz, final Object outerInstance) {

            this.clazz = clazz;
            this.outerInstance = outerInstance;
        }

        /**
         * Sets the expected type of exception and message that must be thrown by the constructor of
         * required class. If you don't want to check exception message, you can set just type
         * of the exception.
         *
         * @param expectedType    type of the exception that must be thrown by the constructor of
         *                        required class, should not be {@code null}.
         * @param expectedMessage message of the exception that must be thrown by the constructor of
         *                        required class, can be {@code null}.
         *
         * @return ExceptionCheckAble.
         */
        public Builder expectedExceptionType(final Class<? extends Throwable> expectedType,
                                             final String expectedMessage) {

            if (expectedType == null) {

                throw new IllegalArgumentException(
                        "Parameter [expectedExceptionType] must not be null!");

            }

            this.expectedExceptionType = expectedType;
            this.expectedExceptionMessage = expectedMessage;

            return this;

        }

        /**
         * Sets the expected type of exception that must be thrown by the constructor of required
         * class.
         *
         * @param expectedType type of the exception that must be thrown by the constructor of
         *                     required class, should not be {@code null}.
         *
         * @return ExceptionCheckAble.
         */
        public Builder expectedExceptionType(final Class<? extends Throwable> expectedType) {

            return this.expectedExceptionType(expectedType, null);

        }

        /**
         * Sets the expected parameters that must match the constructor parameters of required
         * class.
         *
         * @param expectedPara type of parameters that must match the constructor parameters of
         *                     required class, should not be {@code null}.
         *
         * @return Builder.
         */
        public Builder expectedWithParameters(final Class<?>... expectedPara) {

            if (expectedPara == null || expectedPara.length == 0) {

                throw new IllegalArgumentException(
                        "Parameter [expectedParameters] must not be null or empty!");

            }

            this.expectedParameters = expectedPara;

            return this;
        }

        /**
         * Runs the check which will assert that required class has one private constructor
         * which throws or not throws exception and may or may not have expected parameters.
         */
        public void check() {

            if (expectedExceptionType != null && this.expectedParameters != null) {
                throw new IllegalArgumentException(
                        "Check an expected type of exception and parameters for an private "
                        + "constructor make no sense!");
            }
            new PrivateConstructorTester(this.clazz,
                                         this.expectedExceptionType,
                                         this.expectedExceptionMessage,
                                         this.outerInstance,
                                         this.expectedParameters).check();

        }
    }
}
