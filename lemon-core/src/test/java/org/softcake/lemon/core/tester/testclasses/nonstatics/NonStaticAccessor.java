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

package org.softcake.lemon.core.tester.testclasses.nonstatics;

/**
 * @author Rene Neubert.
 */
public final class NonStaticAccessor {

    @SuppressWarnings("all")
    private static String UTILITY_CLASS = "Utility class";

    private NonStaticAccessor() {

    }

    /**
     * Private nonstatic class with package-private constructor.
     *
     * <p> Non compliant Code! </p>
     */
    private final class NonStaticPrivate {

        private NonStaticPrivate() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private nonstatic class with package-private constructor.
     *
     * <p> Non compliant Code! </p>
     */
    private final class NonStaticPackage {

        NonStaticPackage() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private nonstatic class with protected constructor.
     *
     * <p> Non compliant Code! </p>
     */
    private final class NonStaticProtected {

        protected NonStaticProtected() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private nonstatic class with public constructor.
     *
     * <p> Non compliant Code! </p>
     */
    private final class NonStaticPublic {

        public NonStaticPublic() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

}
