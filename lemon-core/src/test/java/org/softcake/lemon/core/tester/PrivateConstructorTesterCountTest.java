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

import org.softcake.lemon.core.tester.testclasses.defaults.DefaultCount;
import org.softcake.lemon.core.tester.testclasses.nonstatics.NonStaticCount;
import org.softcake.lemon.core.tester.testclasses.statics.StaticCount;
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
@SuppressWarnings("all")
public class PrivateConstructorTesterCountTest {

    private static final String UTILITY = "Utility ";

    private static final String SHOULD_HAVE = " should have only one private constructor";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void forClass_defaultClassHasOnlyOneConstructor() {

        Class<DefaultCount> aClass = DefaultCount.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(UTILITY + aClass.toString() + SHOULD_HAVE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_defaultClassHasOnlyOneConstructor_throws() {

        Class<DefaultCount> aClass = DefaultCount.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(UTILITY + aClass.toString() + SHOULD_HAVE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_nonstaticClassHasOnlyOneConstructor_throws() throws Exception {

        String classShortName = "NonStaticTwoConstructors";
        Class<NonStaticCount> aClass = NonStaticCount.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});

        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(UTILITY + aClass.toString() + "$" + classShortName + SHOULD_HAVE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_staticClassHasOnlyOneConstructor_throws() throws Exception {

        String classShortName = "StaticTwo";
        Class<StaticCount> aClass = StaticCount.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(UTILITY + aClass.toString() + "$" + classShortName + SHOULD_HAVE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

}

