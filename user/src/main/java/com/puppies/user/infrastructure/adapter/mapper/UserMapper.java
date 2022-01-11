package com.puppies.user.infrastructure.adapter.mapper;

import com.puppies.user.domain.model.User;
import com.puppies.user.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserEntity toEntity(User user) {
    return UserEntity.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dob(user.getDob())
        .build();
  }

  public User toUser(UserDetailDto userDetailDto) {
    return User.builder()
        .id(userDetailDto.getId())
        .firstName(userDetailDto.getFirstName())
        .lastName(userDetailDto.getLastName())
        .username(userDetailDto.getUsername())
        .email(userDetailDto.getEmail())
        .dob(userDetailDto.getDob())
        .build();
  }

}
