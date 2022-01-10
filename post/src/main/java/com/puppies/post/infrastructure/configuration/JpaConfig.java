package com.puppies.post.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.puppies.post.infrastructure.entity"
})
@EnableJpaRepositories(basePackages = {

})
public class JpaConfig {

}
