package com.puppies.user.infrastructure.adapter.dao;

import com.puppies.security.auth.repository.ApplicationUserRepository;
import com.puppies.user.domain.port.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDaoImpl implements UserDao {

  private final ApplicationUserRepository repository;

  @Override
  public Boolean existsByEmail(String email) {
    return this.repository.existByEmail(email);
  }
}
