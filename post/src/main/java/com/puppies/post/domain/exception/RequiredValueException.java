package com.puppies.post.domain.exception;

public class RequiredValueException extends RuntimeException {

  public RequiredValueException(String message) {
    super(message);
  }
}
