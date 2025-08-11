package com.bank.auth.auth_services.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
  ACTIVE("active"),
  INACTIVE("inactive"),
  PENDING("pending"),
  LOCKED("locked");

  private final String status;
}
