/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.modulith.events;

import java.time.Instant;
import java.util.Optional;

/**
 * An event publication that can be completed.
 *
 * @author Oliver Drotbohm
 */
public interface CompletableEventPublication extends EventPublication {

	/**
	 * Returns the completion date of the publication.
	 *
	 * @return will never be {@literal null}.
	 */
	Optional<Instant> getCompletionDate();

	/**
	 * Returns whether the publication o
	 *
	 * @return
	 */
	default boolean isPublicationCompleted() {
		return getCompletionDate().isPresent();
	}

	/**
	 * Marks the event publication as completed.
	 *
	 * @return
	 */
	CompletableEventPublication markCompleted();

	/**
	 * Creates a {@link CompletableEventPublication} for the given event an listener identifier.
	 *
	 * @param event must not be {@literal null}.
	 * @param id must not be {@literal null}.
	 * @return
	 */
	static CompletableEventPublication of(Object event, PublicationTargetIdentifier id) {
		return DefaultEventPublication.of(event, id);
	}
}
