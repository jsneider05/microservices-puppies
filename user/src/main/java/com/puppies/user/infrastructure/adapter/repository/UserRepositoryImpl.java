package com.puppies.user.infrastructure.adapter.repository;

import com.puppies.security.auth.entity.UserSecurityEntity;
import com.puppies.security.auth.repository.ApplicationUserRepository;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.port.UserRepository;
import com.puppies.user.infrastructure.adapter.CustomerJpaRepository;
import com.puppies.user.infrastructure.adapter.mapper.CustomerMapper;
import com.puppies.user.infrastructure.adapter.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

  private final ApplicationUserRepository applicationUserRepository;
  private final UserMapper userMapper;
  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerMapper customerMapper;

  @Override
  public UUID create(User user) {
    UserSecurityEntity userCreated = applicationUserRepository.create(
        userMapper.toUserEntity(user));
    return customerJpaRepository.save(customerMapper.toEntity(userCreated, user)).getId();
  }
}
