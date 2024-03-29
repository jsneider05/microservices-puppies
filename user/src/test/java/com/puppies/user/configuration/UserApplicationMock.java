package com.puppies.user.configuration;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan(basePackages = {"com.puppies.user"})
public class UserApplicationMock {

  @Bean(initMethod = "migrate")
  Flyway flyway(DataSource dataSource) {

    return Flyway.configure().locations("db/migration/h2")
        .baselineOnMigrate(true)
        .dataSource(dataSource).load();
  }

}
