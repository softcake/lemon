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

import org.softcake.lemon.core.tester.testclasses.defaults.DefaultParameterPrivate;
import org.softcake.lemon.core.tester.testclasses.nonstatics.NonStaticParameter;
import org.softcake.lemon.core.tester.utils.TestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * PrivateConstructorTesterTest Class.
 *
 * @author Rene Neubert @softcake.org
 */
@SuppressWarnings("all")
public class PrivateConstructorTesterParameterTest {

    private static final String MISMATCH_IN = "Mismatch in ";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void expectedWithParameters_defaultWithParameter_assert() {

        Class<DefaultParameterPrivate> aClass = DefaultParameterPrivate.class;
        PrivateConstructorTester.forClass(aClass)
                                .expectedWithParameters(String.class, int.class)
                                .check();
    }

    @Test
    public void expectedWithParameters_defaultWithParameterNotMatch_throw() {

        Class<DefaultParameterPrivate> aClass = DefaultParameterPrivate.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(MISMATCH_IN + aClass.toString() + " " + "constructors parameter(s)");

        PrivateConstructorTester.forClass(aClass).expectedWithParameters(int.class).check();
    }

    @Test
    public void expectedWithParameters_nonstaticWithParameter_assert() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticParameter> aClass = NonStaticParameter.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).expectedWithParameters(
                String.class,
                int.class).check();
    }

    @Test
    public void expectedWithParameters_nonstaticWithParameterNotMatch_throw() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticParameter> aClass = NonStaticParameter.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(MISMATCH_IN
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + " constructors parameter(s)");
        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedWithParameters(int.class)
                                .check();
    }

    @Test
    public void forClass_defaultWithParameterNotMatch_throw() {

        Class<DefaultParameterPrivate> aClass = DefaultParameterPrivate.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(aClass.toString()
                             + " has non-default constructor with some parameters");

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass__nonstaticWithParameterNotMatch_throw() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticParameter> aClass = NonStaticParameter.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(aClass.toString()
                             + "$"
                             + classShortName
                             + " has non-default constructor with some parameters");
        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }
}

