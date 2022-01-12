package com.puppies.post.infrastructure.adapter.repository;

import com.puppies.post.domain.model.Post;
import com.puppies.post.domain.port.repository.PostRepository;
import com.puppies.post.infrastructure.adapter.PostJpaRepository;
import com.puppies.post.infrastructure.adapter.mapper.PostMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

  private final PostJpaRepository jpaRepository;
  private final PostMapper mapper;

  @Override
  public UUID save(Post post) {
    return jpaRepository.save(mapper.toEntity(post)).getId();
  }

  @Override
  public void updatePostImageKey(UUID postId, String imageKey) {
    this.jpaRepository.updateUserImageKey(postId, imageKey, LocalDateTime.now());
  }
}
