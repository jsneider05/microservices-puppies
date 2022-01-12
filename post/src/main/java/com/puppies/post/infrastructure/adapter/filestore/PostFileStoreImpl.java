package com.puppies.post.infrastructure.adapter.filestore;

import com.puppies.post.domain.exception.InternalProcessException;
import com.puppies.post.domain.port.filestore.PostFileStore;
import com.puppies.post.infrastructure.adapter.TemporalPostImageJpaRepository;
import com.puppies.post.infrastructure.entity.TemporalPostImageStorageEntity;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PostFileStoreImpl implements PostFileStore {

  private final TemporalPostImageJpaRepository jpaRepository;

  @Override
  public void save(String path, String filename, Optional<Map<String, String>> optionalMetadata,
      InputStream inputStream) {
    try {
      jpaRepository.save(
          TemporalPostImageStorageEntity.builder()
              .filename(filename)
              .path(path)
              .image(inputStream.readAllBytes())
              .build()
      );
    } catch (IOException e) {
      throw new InternalProcessException(String.format("Failed to store file %s", e));
    }

  }
}
