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

import org.softcake.lemon.core.tester.testclasses.defaults.DefaultAccessorPackage;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultAccessorPrivate;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultAccessorProtected;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultAccessorPublic;
import org.softcake.lemon.core.tester.testclasses.defaults.DefaultEmpty;
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
public class PrivateConstructorTesterAccessTest {

    private static final String CONSTRUCTOR_OF = "Constructor of ";

    private static final String MUST_BE_PRIVATE = " must be private";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void forClass_defaultClassHasDefaultConstructor_throws() {

        Class<DefaultEmpty> aClass = DefaultEmpty.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF + aClass.toString() + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_defaultHasPrivateConstructorNoExpectedExeption_throw() {

        Class<DefaultAccessorPrivate> aClass = DefaultAccessorPrivate.class;
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("For " + aClass.toString() + " no exception was expected");

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_defaultHasPackageConstructor_throw() {

        Class<DefaultAccessorPackage> aClass = DefaultAccessorPackage.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF + aClass.toString() + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_defaultHasPublicConstructor_throw() {

        Class<DefaultAccessorPublic> aClass = DefaultAccessorPublic.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF + aClass.toString() + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_defaultHasProtectedConstructor_throw() {

        Class<DefaultAccessorProtected> aClass = DefaultAccessorProtected.class;
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF + aClass.toString() + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(aClass).check();
    }

    @Test
    public void forClass_nonstaticHasPrivateConstructorNoExpectedExeption_throw() {

        String classShortName = "NonStaticPrivate";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;

        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);

        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("For "
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + " no exception was expected");

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_nonstaticHasPackageConstructor_throw() {

        String classShortName = "NonStaticPackage";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});

        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_nonstaticHasProtectedConstructor_throw() {

        String classShortName = "NonStaticProtected";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_nonstaticHasPublicConstructor_throw() {

        String classShortName = "NonStaticPublic";
        Class<NonStaticAccessor> aClass = NonStaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_staticHasPrivateConstructorNoExpectedExeption_throw() {

        String classShortName = "StaticPrivate";
        Class<StaticAccessor> aClass = StaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("For "
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + " no exception was expected");

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_staticHasPackageConstructor_throw() {

        String classShortName = "StaticPackage";
        Class<StaticAccessor> aClass = StaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_staticHasProtectedConstructor_throw() {

        String classShortName = "StaticProtected";
        Class<StaticAccessor> aClass = StaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

    @Test
    public void forClass_staticHasPublicConstructor_throw() {

        String classShortName = "StaticPublic";
        Class<StaticAccessor> aClass = StaticAccessor.class;
        Object enclosingInstance = TestUtil.getEnclosingInstance(aClass,
                                                                 new Object[]{},
                                                                 new Class<?>[]{});
        Class<?> nestedClass = TestUtil.forNestedName(classShortName, enclosingInstance);
        thrown.expect(AssertionError.class);
        thrown.expectMessage(CONSTRUCTOR_OF
                             + aClass.toString()
                             + "$"
                             + classShortName
                             + MUST_BE_PRIVATE);

        PrivateConstructorTester.forClass(nestedClass, enclosingInstance).check();
    }

}

