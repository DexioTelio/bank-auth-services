package com.bank.auth.auth_services.util;

import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;
import lombok.Getter;

import java.util.*;

@Getter
public final class Result<T, K extends BaseErrorCode> {
  private final T value;
  private final boolean isSuccess;
  private final Set<K> errorCode;
  private final List<String> errors;

  private Result(T value, boolean isSuccess, Set<K> errorCode, List<String> errors) {
    this.value = value;
    this.isSuccess = isSuccess;
    this.errorCode = errorCode == null ? Collections.emptySet() : Set.copyOf(errorCode);
    this.errors = errors == null ? Collections.emptyList() : Collections.unmodifiableList(errors);
  }

  public static <T, K extends BaseErrorCode> Result<T, K> success(T value) {
    return new Result<>(value, true, null, null);
  }

  public static <K extends BaseErrorCode> Result<Void, K> success() {
    return new Result<>(null, true, null, null);
  }

  public static <T, K extends BaseErrorCode> Result<T, K> failure(List<String> errors) {
    return new Result<>(null, false, null, errors);
  };

  public static <T, K extends BaseErrorCode> Result<T, K> failure(String errors) {
    return new Result<>(null, false, null, Collections.singletonList(errors));
  }

  public static <T, K extends BaseErrorCode> Result<T, K> failure(Set<K> errorCode, List<String> errors) {
    return new Result<>(null, false, errorCode, errors);
  }

  public static <T, K extends BaseErrorCode> Result<T, K> failure(K errorCode, String errors) {
    return new Result<>(null, false, Set.of(errorCode), Collections.singletonList(errors));
  }

  public List<String> getUniqueErrorList() {
    return errors.stream().distinct().toList();
  }

  public Set<String> getUniqueErrorSet() {
    return new HashSet<>(errors);
  }

  public Optional<K> getPrimaryErrorCode() {
    return errorCode.stream().findFirst();
  }

  public boolean isFailure() { return !isSuccess; }
  public boolean isSuccess() { return isSuccess; }
}
