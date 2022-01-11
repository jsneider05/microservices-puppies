package com.puppies.user.infrastructure.adapter.dao;

import com.puppies.user.domain.model.User;
import com.puppies.user.domain.port.dao.UserDao;
import com.puppies.user.infrastructure.adapter.UserJpaRepository;
import com.puppies.user.infrastructure.adapter.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

  private final UserJpaRepository jpaRepository;
  private final UserMapper mapper;

  @Override
  public User getUserDetailsById(UUID userId) {
    return mapper.toUser(jpaRepository.selectUserDataById(userId.toString()));
  }
}
