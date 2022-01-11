package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserSecurityEntity;
import com.puppies.security.auth.model.ApplicationUser;
import java.util.Optional;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserRolePrivilegesByUsername(String username);

    Optional<ApplicationUser> selectApplicationMainUserDataByUsername(String username);

    UserSecurityEntity create(UserSecurityEntity userSecurityEntity);

    Boolean existByEmail(String email);

}
