package com.puppies.user.infrastructure.adapter;

import com.puppies.user.infrastructure.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

}
