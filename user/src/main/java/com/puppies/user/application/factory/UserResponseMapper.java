package com.puppies.user.application.factory;

import com.puppies.user.application.response.UserDetailResponse;
import com.puppies.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

  public UserDetailResponse toUserDetailResponse(User user) {
    return UserDetailResponse.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .username(user.getUsername())
        .email(user.getEmail())
        .dob(user.getDob())
        .build();
  }

}
