package com.puppies.user.domain.port.repository;

import com.puppies.user.domain.model.User;
import java.util.UUID;

public interface UserSecurityRepository {

  UUID create(User user);

}
