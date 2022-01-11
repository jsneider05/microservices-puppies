package com.puppies.user.infrastructure.adapter.mapper;

import java.time.LocalDate;
import java.util.UUID;

public interface UserDetailDto {

  UUID getId();

  String getFirstName();

  String getLastName();

  String getUsername();

  String getEmail();

  LocalDate getDob();

}
