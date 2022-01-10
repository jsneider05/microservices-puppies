package com.puppies.user.domain.validator;

import com.puppies.user.domain.model.User;
import java.util.function.Predicate;

public interface UserValidator extends Validation<User> {

  static UserValidator validEmail(boolean isUniqueEmail) {
    return holds(user -> isUniqueEmail, "Email already exist");
  }

  private static UserValidator holds(Predicate<User> p, String message) {
    return account -> p.negate().test(account) ? ValidationResult.ok() : ValidationResult.fail(message);
  }

}
