package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserSecurityEntity;
import com.puppies.security.auth.model.ApplicationUser;

public interface ApplicationUserRepository {

    ApplicationUser selectApplicationUserRolePrivilegesByUsername(String username);

    ApplicationUser selectApplicationMainUserDataByUsername(String username);

    UserSecurityEntity create(UserSecurityEntity userSecurityEntity);

    Boolean existByEmail(String email);

}
