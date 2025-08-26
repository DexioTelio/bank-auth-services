package com.bank.auth.auth_services.exceptions;

import com.bank.auth.auth_services.enums.AuthErrorCode;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
  private final AuthErrorCode errorCode;

  public UserAlreadyExistsException(String username) {
    super("The user '" + username + "' already exists");
    this.errorCode = AuthErrorCode.USERNAME_ALREADY_EXISTS;
  }
}
