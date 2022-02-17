/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.contextpropagation;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Loads {@link ThreadLocalAccessor} and {@link ContextAccessor}.
 */
@SuppressWarnings("rawtypes")
final class ContextAccessorLoader {

    private static final ServiceLoader<ContextAccessor> propagators = ServiceLoader.load(ContextAccessor.class);

    static List<ContextAccessor> getContextAccessorsForSet(Object ctx) {
        List<ContextAccessor> accessors = new ArrayList<>();
        for (ContextAccessor accessor : propagators) {
            if (accessor.supportsContextForSet(ctx)) {
                accessors.add(accessor);
            }
        }
        return accessors;
    }

    static List<ContextAccessor> getContextAccessorsForGet(Object ctx) {
        List<ContextAccessor> accessors = new ArrayList<>();
        for (ContextAccessor accessor : propagators) {
            if (accessor.supportsContextForGet(ctx)) {
                accessors.add(accessor);
            }
        }
        return accessors;
    }
}
