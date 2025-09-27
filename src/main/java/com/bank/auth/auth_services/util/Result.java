package com.bank.auth.auth_services.util;

import com.bank.auth.auth_services.enums.AuthErrorCode;
import com.bank.auth.auth_services.enums.interfaces.BaseErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public record Result<T>(T value, boolean isSuccess, Set<BaseErrorCode> errorCode, List<String> errors) {
  private static final Logger logger = LoggerFactory.getLogger(Result.class);
  public Result {
    errors = errors == null ? Collections.emptyList() : Collections.unmodifiableList(errors);
    errorCode = errorCode == null ? Collections.emptySet() : Set.copyOf(errorCode);
  }

  private static <T> Result<T> validate(Result<T> result) {
    List<String> inconsistencies = new ArrayList<>();

    if (result.isSuccess && (!result.errors.isEmpty() || !result.errorCode().isEmpty())) {
      inconsistencies.add("Successful result cannot have errors or code error");
    }
    if (!result.isSuccess && result.errors.isEmpty()) {
      inconsistencies.add(("Failed result require at least 1 error"));
    }
    if (inconsistencies.isEmpty()) {
      return result;
    } else {
      return new Result<>(null,
              false,
              Set.of(AuthErrorCode.INTERNAL_ERROR),
              List.of("Result validation warning: " + String.join(", ", inconsistencies)));
    }
  }

  public static <T> Result<T> success(T value) {
    return validate(new Result<>(value, true, null, null));
  }

  public static Result<Void> success() {
    return validate(new Result<>(null, true, null, null));
  }

  public static <T> Result<T> failure(List<String> errors) {
    return validate(new Result<>(null, false, null, errors));
  }

  public static <T> Result<T> failure(String errors) {
    return validate(new Result<>(null, false, null, Collections.singletonList(errors)));
  }

  public static <T> Result<T> failure(Set<BaseErrorCode> errorCode, List<String> errors) {
    return validate(new Result<>(null, false, errorCode, errors));
  }

  public static <T> Result<T> failure(BaseErrorCode errorCode, String errors) {
    return validate(new Result<>(null, false, Set.of(errorCode), Collections.singletonList(errors)));
  }

  public List<String> getUniqueErrorList() {
    return errors().stream().distinct().toList(); }

  public Set<String> getUniqueErrorSet() {
    return new HashSet<>(errors());
  }

  public Optional<BaseErrorCode> getPrimaryErrorCode() {
    return errorCode().stream().findFirst();
  }

  public boolean isFailure() { return !isSuccess; }
  public boolean isSuccess() { return isSuccess; }
}
