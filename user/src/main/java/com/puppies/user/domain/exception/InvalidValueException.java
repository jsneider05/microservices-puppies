package com.puppies.user.domain.exception;

public class InvalidValueException extends RuntimeException {

  public InvalidValueException(String message) {
    super(message);
  }
}
