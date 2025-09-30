package com.bank.auth.auth_services.dto;

import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;

import java.time.LocalDateTime;
import java.util.Set;
// ErrorResponse is intended for HTTP responses.
public record ErrorResponse<T extends BaseErrorCode>(
        int status,
        String error,
        T errorCode,
        Set<String> messages,
        ErrorDetails details,
        String path,
        LocalDateTime timestamp
) {

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, String error, T errorCode, Set<String> messages, ErrorDetails details, String path) {
    return new ErrorResponse<>(status, error, errorCode, messages, details, path, LocalDateTime.now());
  }

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, String error, T errorCode, Set<String> messages, String path) {
    return new ErrorResponse<>(status, error, errorCode, messages, null, path, LocalDateTime.now());
  }

  public static <T extends BaseErrorCode> ErrorResponse<T> from(int status, String error, T errorCode, String path) {
    return new ErrorResponse<>(status, error, errorCode, null, null, path, LocalDateTime.now());
  }

  public record ErrorDetails(
          String providedValue,
          Set<String> acceptedValues
  ) {}
}