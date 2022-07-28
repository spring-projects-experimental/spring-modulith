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
package org.springframework.modulith.events.jdbc;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.modulith.events.EventSerializer;
import org.springframework.modulith.events.config.EventPublicationConfigurationExtension;

/**
 * @author Dmitry Belyaev
 * @author Björn Kieling
 * @author Oliver Drotbohm
 */
@Configuration(proxyBeanMethods = false)
class JdbcEventPublicationAutoConfiguration implements EventPublicationConfigurationExtension {

	@Bean
	DatabaseType databaseType(DataSource dataSource) {
		var databaseDriver = DatabaseDriver.fromDataSource(dataSource);
		return DatabaseType.from(databaseDriver);
	}
	@Bean
	JdbcEventPublicationRepository jdbcEventPublicationRepository(JdbcTemplate jdbcTemplate,
			EventSerializer serializer, DatabaseType databaseType) {
		return new JdbcEventPublicationRepository(jdbcTemplate, serializer, databaseType);
	}

	@Bean
	@ConditionalOnProperty(name = "spring.modulith.events.schema-initialization.enabled", havingValue = "true")
	DatabaseSchemaInitializer databaseSchemaInitializer(JdbcTemplate jdbcTemplate, DatabaseType databaseType) {
		return new DatabaseSchemaInitializer(jdbcTemplate, databaseType);
	}
}
