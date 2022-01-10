package com.puppies.user.application.factory;

import com.puppies.user.application.request.CreateUserRequest;
import com.puppies.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFactory {

  private final PasswordEncoder passwordEncoder;

  public User create(CreateUserRequest request) {
    return User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .username(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .dob(request.getDob())
        .build();
  }

}
