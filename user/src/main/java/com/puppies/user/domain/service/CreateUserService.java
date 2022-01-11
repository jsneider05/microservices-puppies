package com.puppies.user.domain.service;

import com.puppies.security.auth.model.UserRoleEnum;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.model.UserRole;
import com.puppies.user.domain.port.UserRoleDao;
import com.puppies.user.domain.port.UserDao;
import com.puppies.user.domain.port.UserRepository;
import com.puppies.user.domain.validator.UserValidator;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateUserService {

  private final UserRepository userRepository;
  private final UserDao userDao;
  private final UserRoleDao userRoleDao;

  public UUID execute(User user) {

    UserValidator.validEmail(userDao.existsByEmail(user.getEmail()))
        .validate(user)
        .throwIfInvalid();

    Set<UserRole> roles = userRoleDao.getAll().stream()
        .filter(userRole -> userRole.getName().equals(UserRoleEnum.CUSTOMER.name()))
        .collect(Collectors.toSet());

    user.setUserRoles(roles);

    return userRepository.create(user);
  }

}
