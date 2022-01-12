package com.puppies.post.domain.port.filestore;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface PostFileStore {

  void save(String path, String filename, Optional<Map<String, String>> optionalMetadata,
      InputStream inputStream);

}
