package com.puppies.user.infrastructure.adapter.repository;

import com.puppies.user.domain.model.User;
import com.puppies.user.domain.port.UserRepository;
import com.puppies.user.infrastructure.adapter.UserJpaRepository;
import com.puppies.user.infrastructure.adapter.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository jpaRepository;
  private final UserMapper mapper;

  @Override
  public UUID create(User user) {
    return jpaRepository.save(mapper.toEntity(user)).getId();
  }
}
