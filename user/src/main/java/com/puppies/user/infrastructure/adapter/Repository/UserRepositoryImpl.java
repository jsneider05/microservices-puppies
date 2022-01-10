package com.puppies.user.infrastructure.adapter.Repository;

import com.puppies.security.auth.repository.ApplicationUserRepository;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.port.UserRepository;
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
  private final UserMapper mapper;

  @Override
  public UUID create(User user) {
    return applicationUserRepository.create(
        mapper.toUserEntity(user)
    );
  }
}
