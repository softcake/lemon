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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import org.softcake.lemon.core.tester.PrivateConstructorTester;
import org.softcake.lemon.core.tester.exeptions.PrivateConstructorTesterException;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultEmpty;
import org.softcake.lemon.core.tester.testclasses.nonstatics.NonStaticAccessor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Rene Neubert
 */
public class TestUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testUtil_constructorTest() throws Exception {

        PrivateConstructorTester.forClass(TestUtil.class).expectedExceptionType(
                IllegalStateException.class,
                "No instances!");
    }

    @Test
    public void forNestedName() throws Exception {

    }

    @Test
    public void forNestedName_parameterClassNameNotNull_throws() {

        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});

        String className = null;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNullOrEmpty("className"));

        TestUtil.forNestedName(className, enclosingInstance);
    }

    @Test
    public void forNestedName_parameterEnclosingInstanceNotNull_throws() {

        String className = "NestedPrivate";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNull("enclosingInstance"));

        TestUtil.forNestedName(className, null);
    }

    @Test
    public void getEnclosingInstance() {

        Class<DefaultEmpty> aClass = DefaultEmpty.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class[]{});
        assertThat(enclosingInstance, instanceOf(DefaultEmpty.class));
    }

    @Test
    public void getEnclosingInstance_throw() {

        Class<DefaultEmpty> aClass = DefaultEmpty.class;
        thrown.expect(PrivateConstructorTesterException.class);
        TestUtil.getEnclosingInstance(aClass, new Object[]{}, new Class[]{String.class});
    }

    @Test
    public void getEnclosingInstance_parameterNotNull_throws() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(getMessageNull("clazz"));
        Class aClass = null;
        TestUtil.getEnclosingInstance(aClass, new Object[]{}, new Class[]{String.class});
    }

    private String getMessageNullOrEmpty(final String parameter) {

        return "Parameter [" + parameter + "] must not be null or empty!";
    }

    private String getMessageNull(final String parameter) {

        return "Parameter [" + parameter + "] must not be null!";
    }
}
