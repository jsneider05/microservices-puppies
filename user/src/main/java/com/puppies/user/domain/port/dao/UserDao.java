package com.puppies.user.domain.port.dao;

import com.puppies.user.domain.model.User;
import java.util.UUID;

public interface UserDao {

  User getUserDetailsById(UUID userId);

}
