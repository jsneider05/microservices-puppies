package com.puppies.security.auth.repository;

import com.puppies.security.auth.model.Role;
import java.util.Set;

public interface RoleRepository {

    Set<Role> getAll();

}
