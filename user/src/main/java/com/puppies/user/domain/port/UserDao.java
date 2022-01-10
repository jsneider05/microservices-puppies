package com.puppies.user.domain.port;

public interface UserDao {

  Boolean existsByEmail(String email);

}
