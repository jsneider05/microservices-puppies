package com.puppies.post.domain.service;

import com.puppies.post.domain.model.Post;
import com.puppies.post.domain.port.repository.PostRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreatePostService {

  private final PostRepository repository;

  // TODO: Change UUID return to Post
  public UUID execute(UUID userId, String caption) {
    return repository.save(Post.builder()
        .userId(userId)
        .caption(caption)
        .build()
    );
  }

}
