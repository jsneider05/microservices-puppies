package com.puppies.post.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  private UUID id;

  private UUID userId;

  private String imageKey;

  private String caption;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<Like> likes;

}
