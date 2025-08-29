package com.bank.auth.auth_services.dto;

import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;

import java.time.LocalDateTime;
import java.util.Set;
// ErrorResponse is intended for HTTP responses.
public record ErrorResponse<T extends BaseErrorCode>(
        int status,
        T errorCode,
        Set<String> messages,
        ErrorDetails details,
        LocalDateTime timestamp
) {

  public ErrorResponse(int status, T errorCode, Set<String> messages, ErrorDetails details) {
    this(status, errorCode, messages, details, LocalDateTime.now());
  }

  public ErrorResponse(int status, T errorCode, Set<String> messages) {
    this(status, errorCode, messages, null, LocalDateTime.now());
  }

  public ErrorResponse(int status, T errorCode) {
    this(status, errorCode, null, null, LocalDateTime.now());
  }

  public record ErrorDetails(
          String providedValue,
          Set<String> acceptedValues
  ) {}
}