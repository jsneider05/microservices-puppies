package com.puppies.user.infrastructure.adapter.mapper;

import com.puppies.security.auth.entity.UserEntity;
import com.puppies.user.domain.model.User;
import com.puppies.user.infrastructure.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public CustomerEntity toEntity(UserEntity userEntity, User user) {
    return CustomerEntity.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dob(user.getDob())
        .user(userEntity)
        .build();
  }

}
