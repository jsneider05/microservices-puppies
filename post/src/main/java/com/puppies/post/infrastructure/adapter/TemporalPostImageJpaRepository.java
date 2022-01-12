package com.puppies.post.infrastructure.adapter;

import com.puppies.post.infrastructure.entity.TemporalPostImageStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporalPostImageJpaRepository extends JpaRepository<TemporalPostImageStorageEntity, String> {

}
