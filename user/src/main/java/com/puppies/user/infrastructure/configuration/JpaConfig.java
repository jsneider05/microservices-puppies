package com.puppies.user.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.puppies.user.infrastructure.entity",
    "com.puppies.security.auth.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.puppies.security.auth.repository",
    "com.puppies.user.infrastructure.adapter"
})
public class JpaConfig {

}
