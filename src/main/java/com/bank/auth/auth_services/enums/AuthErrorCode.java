package com.bank.auth.auth_services.enums;

public enum AuthErrorCode {
  USERNAME_ALREADY_EXISTS("Username already exists"),
  PASSWORD_TOO_WEAK("password too wear"),
  ACCOUNT_LOCKED("account locked");

  AuthErrorCode(String message) {
  }
}
