package com.bank.auth.auth_services.enums;

import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;

public enum AuthErrorCode implements BaseErrorCode {
  USERNAME_ALREADY_EXISTS(409, "Username already exists"),
  PASSWORD_TOO_WEAK(400, "password too wear"),
  ACCOUNT_LOCKED(423, "account locked"),
  INVALID_CREDENTIALS(401, "Invalid credentials"),
  TOKEN_EXPIRED(401, "Authentication toke expired"),
  USER_NOT_FOUND(404, "User not found");

  private final int status;
  private final String message;

  AuthErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
