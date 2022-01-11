package com.puppies.user.application.factory;

import com.puppies.security.auth.service.UserSecurityService;
import com.puppies.user.application.request.CreateUserRequest;
import com.puppies.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFactory {

  private final UserSecurityService userSecurityService;

  public User create(CreateUserRequest request) {
    return User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .username(request.getEmail())
        .password(userSecurityService.encodePassword(request.getPassword()))
        .dob(request.getDob())
        .build();
  }

}
