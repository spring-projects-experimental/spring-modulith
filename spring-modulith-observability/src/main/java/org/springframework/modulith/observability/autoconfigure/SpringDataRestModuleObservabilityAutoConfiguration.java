/*
 * Copyright 2022 the original author or authors.
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
package org.springframework.modulith.observability.autoconfigure;

import org.springframework.modulith.observability.ApplicationRuntime;
import org.springframework.modulith.observability.SpringDataRestModuleTracingBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.RepositoryController;

/**
 * @author Oliver Drotbohm
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RepositoryController.class)
class SpringDataRestModuleObservabilityAutoConfiguration {

	@Bean
	static SpringDataRestModuleTracingBeanPostProcessor springDataRestModuleTracingBeanPostProcessor(
			ApplicationRuntime runtime, Tracer tracer) {
		return new SpringDataRestModuleTracingBeanPostProcessor(runtime, tracer);
	}
}
