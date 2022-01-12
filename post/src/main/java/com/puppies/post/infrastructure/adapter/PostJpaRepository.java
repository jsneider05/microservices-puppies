package com.puppies.post.infrastructure.adapter;

import com.puppies.post.infrastructure.entity.PostEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostJpaRepository extends JpaRepository<PostEntity, UUID> {

  @Modifying
  @Query("update PostEntity p set p.imageKey = :imageKey, p.updatedAt = :updatedAt where p.id = :postId")
  void updateUserImageKey(
      @Param(value = "postId") UUID postId,
      @Param(value = "imageKey") String imageKey,
      @Param(value = "updatedAt") LocalDateTime updatedAt
  );

}
