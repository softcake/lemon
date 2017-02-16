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
public final class StaticParameter {

    @SuppressWarnings("all")
    private static String UTILITY_CLASS = "Utility class";

    private StaticParameter() {

        throw new IllegalAccessError(UTILITY_CLASS);
    }

    /**
     * Private static class with private constructor with parameter.
     *
     * <p> Compliant Solution!</p>
     */
    public static final class StaticCompliant {

        private final String message;

        private final boolean count;

        private StaticCompliant(final String msg, final boolean cnt) {

            message = msg;
            count = cnt;
        }

    }

}
