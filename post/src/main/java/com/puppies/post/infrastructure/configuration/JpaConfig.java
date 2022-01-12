package com.puppies.post.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.puppies.post.infrastructure.entity",
    "com.puppies.security.auth.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.puppies.post.infrastructure.adapter",
    "com.puppies.security.auth.repository"
})
public class JpaConfig {

}
