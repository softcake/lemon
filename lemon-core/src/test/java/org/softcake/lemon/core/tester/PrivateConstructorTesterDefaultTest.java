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

import org.softcake.lemon.core.tester.exeptions.PrivateConstructorTesterException;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultCount;
import org.softcake.lemon.core.tester.utils.TestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import org.junit.Test;

/**
 * PrivateConstructorTesterTest Class.
 *
 * @author Rene Neubert @softcake.org
 */
public class PrivateConstructorTesterDefaultTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void forClassClass_parameterNotNull_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNull("clazz"));
        Class<?> aClass = null;
        PrivateConstructorTester.forClass(aClass);
    }

    @Test
    public void forClassClassInstance_parameterNotNull_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNull("clazz"));
        Class<?> aClass = null;
        PrivateConstructorTester.forClass(aClass, null);
    }

    @Test
    @SuppressWarnings("all")
    public void expectedExceptionType_parameterNotNull_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNull("expectedExceptionType"));
        PrivateConstructorTester.forClass(Object.class).expectedExceptionType(null);
    }

    @Test
    @SuppressWarnings("all")
    public void expectedWithParameters_parameterNotNull_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNullOrEmpty("expectedParameters"));
        Class<?>[] classes = null;
        PrivateConstructorTester.forClass(Object.class).expectedWithParameters(classes);
    }

    @Test
    @SuppressWarnings("all")
    public void expectedWithParameters_parameterEmpty_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNullOrEmpty("expectedParameters"));
        Class<?>[] classes = new Class<?>[]{};
        PrivateConstructorTester.forClass(Object.class).expectedWithParameters(classes);
    }

    @Test
    public void check_expectedExceptionTypeAndExpectedWithParameters_throwsException() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Check an expected type of exception and parameters for an "
                             + "private constructor make no sense!");
        PrivateConstructorTester.forClass(Object.class).expectedExceptionType(
                IllegalArgumentException.class).expectedWithParameters(String.class).check();
    }

    @Test
    public void getEnclosingInstance_cantInstantiateClass_throws() {

        thrown.expect(PrivateConstructorTesterException.class);
        TestUtil.getEnclosingInstance(DefaultCount.class,
                                      new Object[]{true},
                                      new Class<?>[]{Boolean.class});
    }

    private String getMessageNullOrEmpty(final String parameter) {

        return "Parameter [" + parameter + "] must not be null or empty!";
    }

    private String getMessageNull(final String parameter) {

        return "Parameter [" + parameter + "] must not be null!";
    }
}

