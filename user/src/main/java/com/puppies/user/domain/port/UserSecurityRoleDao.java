package com.puppies.user.domain.port;

import com.puppies.user.domain.model.UserRole;
import java.util.Set;

public interface UserSecurityRoleDao {

  Set<UserRole> getAll();

}
