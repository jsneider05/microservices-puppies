package com.puppies.user.domain.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private UUID id;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private LocalDate dob;

  private String password;

  private Set<UserRole> userRoles;

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
