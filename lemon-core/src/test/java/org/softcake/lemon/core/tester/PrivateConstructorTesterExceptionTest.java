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

import org.softcake.lemon.core.tester.testclasses.defaults.DefaultAccessorPrivate;
import org.softcake.lemon.core.tester.testclasses.nonstatics.NonStaticAccessor;
import org.softcake.lemon.core.tester.testclasses.statics.StaticAccessor;
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
public class PrivateConstructorTesterExceptionTest {

    private static final String MISMATCH_IN = "Mismatch in ";

    private static String UTILITY_CLASS = "Utility class";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void expectedExceptionType_defaultClassConstructorThrowsException_assert() {

        PrivateConstructorTester.forClass(DefaultAccessorPrivate.class)
                                .expectedExceptionType(IllegalAccessError.class)
                                .check();
    }

    @Test
    public void expectedExceptionType_defaultClassConstructorThrowsExceptionWithMessage_assert() {

        PrivateConstructorTester.forClass(DefaultAccessorPrivate.class)
                                .expectedExceptionType(IllegalAccessError.class, UTILITY_CLASS)
                                .check();
    }
    @Test
    public void expectedExceptionType_nonstaticClassConstructorThrowsException_assert() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(IllegalAccessError.class)
                                .check();
    }
    @Test
    public void expectedExceptionType_nonstaticClassConstructorThrowsExceptionWithMessage_assert() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(IllegalAccessError.class, UTILITY_CLASS)
                                .check();
    }

    @Test
    public void expectedExceptionType_staticClassConstructorThrowsException_assert() {

        String classShortName = "StaticPrivate";
        Class<StaticAccessor> aClass = StaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(IllegalAccessError.class)
                                .check();
    }

    @Test
    public void expectedExceptionType_staticClassConstructorThrowsExceptionWithMessage_assert() {

        String classShortName = "StaticPrivate";
        Class<StaticAccessor> aClass = StaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(IllegalAccessError.class, UTILITY_CLASS)
                                .check();
    }

    @Test
    public void expectedExceptionType_defaultClassExpectedExceptionNotMatch_throw() {
        Class<DefaultAccessorPrivate> aClass = DefaultAccessorPrivate.class;
        thrown.expect(AssertionError.class);
        PrivateConstructorTester.forClass(aClass)
                                .expectedExceptionType(NullPointerException.class)
                                .check();
    }
    @Test
    public void expectedExceptionType_defaultClassExpectedExceptionWithMessageNotMatch_throw() {
        Class<DefaultAccessorPrivate> aClass = DefaultAccessorPrivate.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(MISMATCH_IN
                             + aClass.toString()
                             + " expected constructor exception");
        PrivateConstructorTester.forClass(aClass)
                                .expectedExceptionType(NullPointerException.class, UTILITY_CLASS)
                                .check();
    }

    @Test
    public void expectedExceptionType_nonstaticClassExpectedExceptionNotMatch_throw() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(NullPointerException.class)
                                .check();
    }

    @Test
    public void expectedExceptionType_nonstaticClassExpectedExceptionWithMessageNotMatch_throw() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(MISMATCH_IN
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + " expected constructor exception");
        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(NullPointerException.class, UTILITY_CLASS)
                                .check();
    }

    @Test
    public void expectedExceptionType_staticClassExpectedExceptionNotMatch_throw() {

        String classShortName = "StaticPrivate";
        Class<StaticAccessor> aClass = StaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(NullPointerException.class)
                                .check();
    }

    @Test
    public void expectedExceptionType_staticClassExpectedExceptionnWithMessageNotMatch_throw() {

        String classShortName = "StaticPrivate";
        Class<StaticAccessor> aClass = StaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(MISMATCH_IN
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + " expected constructor exception");
        PrivateConstructorTester.forClass(nestedClass, enclosingInstance)
                                .expectedExceptionType(NullPointerException.class, UTILITY_CLASS)
                                .check();
    }

}

