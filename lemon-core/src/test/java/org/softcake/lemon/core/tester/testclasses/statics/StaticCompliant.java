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
public final class StaticCompliant {

    @SuppressWarnings("all")
    private static String UTILITY_CLASS = "Utility class";

    private StaticCompliant() {

        throw new IllegalAccessError(UTILITY_CLASS);
    }

    /**
     * Package-private static class.
     */
    static final class StaticPackage {

        private StaticPackage() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private static class.
     */
    private static final class StaticPrivate {

        private StaticPrivate() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Protected static class.
     */
    protected static final class StaticProtected {

        private StaticProtected() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Public static class.
     */
    public static final class StaticPublic {

        private StaticPublic() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Package-private nested class.
     */
    final class NestedPackage {

        private NestedPackage() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Private nested class.
     */
    private final class NestedPrivate {

        private NestedPrivate() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Protected nested class.
     */
    protected final class NestedProtected {

        private NestedProtected() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

    /**
     * Public nested class.
     */
    public final class NestedPublic {

        private NestedPublic() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }
    }

}
