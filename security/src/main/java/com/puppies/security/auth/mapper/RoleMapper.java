package com.puppies.security.auth.mapper;

import com.puppies.security.auth.entity.RoleEntity;
import com.puppies.security.auth.model.Role;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  public Set<Role> toRoles(List<RoleEntity> roleEntities) {
    return roleEntities.stream()
        .map(this::toRole)
        .collect(Collectors.toSet());
  }

  public Role toRole(RoleEntity roleEntity) {
    return Role.builder()
        .id(roleEntity.getId())
        .name(roleEntity.getName())
        .build();
  }

}
