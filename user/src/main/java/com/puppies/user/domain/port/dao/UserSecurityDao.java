package com.puppies.user.domain.port.dao;

public interface UserSecurityDao {

  Boolean existsByEmail(String email);

}
