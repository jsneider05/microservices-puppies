package com.puppies.post.infrastructure.adapter.mapper;

import com.puppies.post.domain.model.Post;
import com.puppies.post.infrastructure.entity.PostEntity;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

  public PostEntity toEntity(Post post) {
    return PostEntity.builder()
        .userId(post.getUserId())
        .caption(post.getCaption())
        .createdAt(LocalDateTime.now())
        .build();
  }

}
