package com.bank.auth.auth_services.enums;

public enum AuthErrorCode implements BaseErrorCode {
  USERNAME_ALREADY_EXISTS(409, "Username already exists"),
  PASSWORD_TOO_WEAK(400, "password too wear"),
  ACCOUNT_LOCKED(423, "account locked");

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
