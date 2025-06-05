package com.ztpai.api.exceptions;

public class InvalidUsernameException extends RuntimeException {
  public InvalidUsernameException(String message) {
    super(message);
  }
}
