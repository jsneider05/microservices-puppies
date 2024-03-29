package com.puppies.security.auth.mapper;

import com.puppies.security.auth.entity.PrivilegeEntity;
import com.puppies.security.auth.entity.RoleEntity;
import com.puppies.security.auth.entity.UserSecurityEntity;
import com.puppies.security.auth.model.ApplicationUser;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserMapper {

  private static final String USER_NOT_FOUND = "User not found";

  public ApplicationUser mapToApplicationUser(UserSecurityEntity userEntity) {
    return Optional.ofNullable(userEntity)
        .map(user ->
            new ApplicationUser(
                user.getUserId(),
                getPermissions(user.getRoles()),
                user.getPassword(),
                user.getUserName(),
                true,
                true,
                true,
                user.isEnabled()))
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
  }

  private Set<SimpleGrantedAuthority> getPermissions(Set<RoleEntity> roleEntities) {
    Set<SimpleGrantedAuthority> permissions = roleEntities.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toSet());

    roleEntities.forEach(
        role -> getRolePrivilegesName(role.getPrivileges())
            .forEach(
                privilegeName -> permissions.add(new SimpleGrantedAuthority(privilegeName))));
    return permissions;
  }

  private Set<String> getRolePrivilegesName(Set<PrivilegeEntity> privilegeEntities) {
    return privilegeEntities.stream()
        .map(PrivilegeEntity::getName)
        .collect(Collectors.toSet());
  }

  public ApplicationUser mapToApplicationMainUserData(UserSecurityEntity userEntity) {
    return Optional.ofNullable(userEntity)
        .map(user -> new ApplicationUser(
            user.getUserId(),
            null,
            null,
            user.getUserName(),
            true,
            true,
            true,
            user.isEnabled()))
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
  }

}
