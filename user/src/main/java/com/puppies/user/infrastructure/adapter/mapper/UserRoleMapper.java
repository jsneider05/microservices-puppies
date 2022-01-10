package com.puppies.user.infrastructure.adapter.mapper;

import com.puppies.security.auth.model.Role;
import com.puppies.user.domain.model.UserRole;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

  public Set<UserRole> toUserRoles(Set<Role> roles) {
    return roles.stream()
        .map(this::toUserRole)
        .collect(Collectors.toSet());
  }

  public UserRole toUserRole(Role role) {
    return UserRole.builder()
        .id(role.getId())
        .name(role.getName())
        .build();
  }

}
