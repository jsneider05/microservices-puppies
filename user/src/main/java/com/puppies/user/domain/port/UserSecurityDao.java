package com.puppies.user.domain.port;

public interface UserSecurityDao {

  Boolean existsByEmail(String email);

}
