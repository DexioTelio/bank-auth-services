package com.bank.auth.auth_services.enums;

import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;

public enum AuthErrorCode implements BaseErrorCode {
  INVALID_CREDENTIALS(401, "Invalid credentials"),
  USER_NOT_FOUND(404, "User not found"),
  DATABASE_ERROR(503, "Database services unavailable"),
  INTERNAL_ERROR(500, "Unexpected internal server error"),
  FIELD_VALIDATION_FAILED(400, "One of more fields are invalid");

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
