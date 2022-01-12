package com.puppies.post.application;

import com.puppies.post.domain.port.repository.PostRepository;
import com.puppies.post.domain.service.CreatePostService;
import com.puppies.post.domain.service.UploadPostImageService;
import com.puppies.security.auth.service.UserSecurityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreatePostHandler {

  private final CreatePostService createPostService;
  private final UploadPostImageService uploadPostImageService;
  private final PostRepository postRepository;
  private final UserSecurityService userSecurityService;

  @Transactional
  public UUID execute(MultipartFile file, String caption) {
    UUID userId = userSecurityService.getUserId();

    UUID postId = createPostService.execute(userId, caption);

    String filename = uploadPostImageService.execute(postId, file);

    postRepository.updatePostImageKey(postId, filename);

    return postId;
  }

}
