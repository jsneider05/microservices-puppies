package com.puppies.post.infrastructure.controller;

import com.puppies.post.application.CreatePostHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestControllerAdvice
@RestController
@RequestMapping("/v1/post")
@Tag(name = "Post", description = "Post Controller")
@Validated
public class PostController {

  private final CreatePostHandler createPostHandler;

  @PostMapping(
      path = "/create",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
  @Operation(summary = "Create Post", description = "Post creation", tags = { "post-creation" })
  public void uploadUserProfileImage(@RequestParam("caption") String caption,
      @RequestParam("file") MultipartFile file) {
    createPostHandler.execute(file, caption);
  }

}
