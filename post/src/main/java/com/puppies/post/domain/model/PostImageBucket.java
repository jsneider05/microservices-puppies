package com.puppies.post.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "application.cloud.client")
@ConstructorBinding
public class PostImageBucket {

  private final String bucketName;

}
