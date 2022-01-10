package com.puppies.user.infrastructure.adapter.mapper;

import com.puppies.security.auth.entity.RoleEntity;
import com.puppies.security.auth.entity.UserEntity;
import com.puppies.user.domain.model.User;
import com.puppies.user.domain.model.UserRole;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserEntity toUserEntity(User user){
    return UserEntity.builder()
        .email(user.getEmail())
        .enabled(Boolean.TRUE)
        .userName(user.getUsername())
        .password(user.getPassword())
        .roles(toRoleEntities(user.getUserRoles()))
        .build();

  }

  private Set<RoleEntity> toRoleEntities(Set<UserRole> userRoles) {
    return userRoles.stream()
        .map(this::toRoleEntity)
        .collect(Collectors.toSet());
  }

  private RoleEntity toRoleEntity(UserRole userRole) {
    return RoleEntity.builder()
        .id(userRole.getId())
        .name(userRole.getName())
        .build();
  }



}
