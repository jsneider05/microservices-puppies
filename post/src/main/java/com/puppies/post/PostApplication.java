package com.puppies.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.puppies.post", "com.puppies.security"})
@ConfigurationPropertiesScan
public class PostApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostApplication.class, args);
  }


}

