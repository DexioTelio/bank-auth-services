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

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, T errorCode, Set<String> messages, ErrorDetails details) {
    return new ErrorResponse<>(status, errorCode, messages, details, LocalDateTime.now());
  }

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, T errorCode, Set<String> messages) {
    return new ErrorResponse<>(status, errorCode, messages, null, LocalDateTime.now());
  }

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, T errorCode) {
    return new ErrorResponse<>(status, errorCode, null, null, LocalDateTime.now());
  }

  public record ErrorDetails(
          String providedValue,
          Set<String> acceptedValues
  ) {}
}