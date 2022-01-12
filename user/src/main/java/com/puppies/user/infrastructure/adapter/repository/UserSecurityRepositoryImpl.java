package com.puppies.user.infrastructure.adapter.repository;

import com.puppies.security.auth.repository.ApplicationUserRepository;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.port.repository.UserSecurityRepository;
import com.puppies.user.infrastructure.adapter.mapper.UserSecurityMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserSecurityRepositoryImpl implements UserSecurityRepository {

  private final ApplicationUserRepository applicationUserRepository;
  private final UserSecurityMapper userSecurityMapper;

  @Override
  public UUID save(User user) {
    return applicationUserRepository.create(
        userSecurityMapper.toEntity(user)).getUserId();
  }
}
