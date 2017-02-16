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

package org.softcake.lemon.core.tester.testclasses.statics;

/**
 * @author Rene Neubert.
 */
public final class StaticAccessor {

    @SuppressWarnings("all")
    private static String UTILITY_CLASS = "Utility class";

    private StaticAccessor() {

    }

    /**
     * Private static class with private constructor.
     *
     * <p> Compliant Solution!</p>
     */
    private static final class StaticPrivate {

        private StaticPrivate() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }

    }

    /**
     * Private static class with package-private constructors.
     *
     * <p> Non compliant Code! </p>
     */
    private static final class StaticPackage {

        StaticPackage() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private static class with protected constructors.
     *
     * <p> Non compliant Code! </p>
     */
    private static final class StaticProtected {

        protected StaticProtected() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private static class with public constructors.
     *
     * <p> Non compliant Code! </p>
     */
    private static final class StaticPublic {

        public StaticPublic() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private nested class with correct constructor.
     *
     * <p> Compliant Solution!</p>
     */
    private static final class NestedCompliant {

        private NestedCompliant() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }

    }
}
