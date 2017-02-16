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
public final class StaticCount {

    @SuppressWarnings("all")
    private static String UTILITY_CLASS = "Utility class";

    private StaticCount() {
    }
    
    /**
     * Private static class with two constructors.
     *
     * <p> Non compliant Code! </p>
     */
    private static final class StaticTwo {

        private final String message;

        private StaticTwo() {

            throw new IllegalAccessError(UTILITY_CLASS);
        }

        private StaticTwo(final String msg) {

            message = msg;
        }
    }

}