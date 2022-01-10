package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserEntity;
import com.puppies.security.auth.model.ApplicationUser;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserByUsername (String username);

    UUID create(UserEntity userEntity);

    Boolean existByEmail(String email);

}
