package com.puppies.security.auth.service;

import java.util.UUID;

public interface UserSecurityService {

  String encodePassword(String password);

  UUID getUserId();

}
