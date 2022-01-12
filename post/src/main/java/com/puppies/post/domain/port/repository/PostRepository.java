package com.puppies.post.domain.port.repository;

import com.puppies.post.domain.model.Post;
import java.util.UUID;

public interface PostRepository {

  UUID save(Post post);

  void updatePostImageKey(UUID postId, String imageKey);

}
