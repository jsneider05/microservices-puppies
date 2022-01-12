package com.puppies.user.domain.service;

import com.puppies.security.auth.model.UserRoleEnum;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.model.UserRole;
import com.puppies.user.domain.port.repository.UserRepository;
import com.puppies.user.domain.port.dao.UserSecurityRoleDao;
import com.puppies.user.domain.port.dao.UserSecurityDao;
import com.puppies.user.domain.port.repository.UserSecurityRepository;
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

  private final UserSecurityRepository userSecurityRepository;
  private final UserSecurityDao userSecurityDao;
  private final UserSecurityRoleDao userSecurityRoleDao;
  private final UserRepository userRepository;

  public UUID execute(User user) {

    UserValidator.validEmail(userSecurityDao.existsByEmail(user.getEmail()))
        .validate(user)
        .throwIfInvalid();

    UUID userIdCreated = userRepository.save(user);

    Set<UserRole> roles = userSecurityRoleDao.getAll().stream()
        .filter(userRole -> userRole.getName().equals(UserRoleEnum.CUSTOMER.name()))
        .collect(Collectors.toSet());

    user.setUserRoles(roles);
    user.setId(userIdCreated);
    userSecurityRepository.save(user);

    return userIdCreated;
  }

}
