package com.puppies.post.domain.service;

import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;
import static org.apache.http.entity.ContentType.IMAGE_GIF;

import com.puppies.post.domain.exception.InternalProcessException;
import com.puppies.post.domain.exception.InvalidValueException;
import com.puppies.post.domain.exception.RequiredValueException;
import com.puppies.post.domain.model.PostImageBucket;
import com.puppies.post.domain.port.filestore.PostFileStore;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadPostImageService {

  private final PostFileStore fileStore;
  private final PostImageBucket postImageBucket;

  // TODO: Change idPost to Post
  public String execute(UUID postId, MultipartFile file) {

    // 1. Check if image is not empty
    checkEmptyImage.accept(file);

    // 2. Check if file is an image
    checkFileImageType.accept(file);

    // 4. Grab some metadata from file if any
    Optional<Map<String, String>> metadata = extractMetadata.apply(file);

    // 5. Store the image
    String filepath = getImagePath(postImageBucket.getBucketName(), postId);
    String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

    try {
      fileStore.save(filepath, filename, metadata, file.getInputStream());
    } catch (IOException e) {
      throw new InternalProcessException(String.valueOf(e));
    }
    return filename;
  }

  private final Consumer<MultipartFile> checkEmptyImage = file -> {
    if (file.isEmpty()) {
      throw new RequiredValueException("Image cant be empty [" + file.getSize() + "]");
    }
  };

  private final Supplier<Stream<String>> imageContentTypeAllowed = () ->
      Stream.of(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType());

  private final Consumer<MultipartFile> checkFileImageType = file -> {
    if (this.imageContentTypeAllowed.get()
        .noneMatch(contentType -> contentType.equals(file.getContentType()))) {
      throw new InvalidValueException("File must be an image [" + file.getContentType() + "]");
    }
  };

  private final Function<MultipartFile, Optional<Map<String, String>>> extractMetadata = file ->
      Optional.of(Map.of(
          "Content-Type", file.getContentType(),
          "Content-Length", String.valueOf(file.getSize())
      ));

  // TODO: Move to Post, Tell don't ask
  private String getImagePath(String bucketName, UUID postId) {
    return String.format("%s/%s", bucketName, postId);
  }

}
