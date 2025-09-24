package com.bank.auth.auth_services.util;

import java.util.*;

public record Result<T>(T value, boolean isSuccess, List<String> errors) {
  public Result {
    errors = Collections.unmodifiableList(errors);
    validateState(isSuccess, errors);
  }

  private void validateState(boolean isSuccess, List<String> errors) {
    if (errors == null) errors = Collections.emptyList();

    List<String> inconsistencies = new ArrayList<>();

    if (isSuccess && !errors.isEmpty()) {
      inconsistencies.add("Successful result cannot have errors");
    }
    if (!isSuccess && errors.isEmpty()) {
      inconsistencies.add(("Failed result require at least 1 error"));
    }
    if (inconsistencies.isEmpty()) {
      System.out.println("Result validation warning: " + inconsistencies);
    }
  }

  public static <T> Result<T> success(T value) {
    return new Result<>(value, true, null);
  }

  public static Result<Void> success() {
    return new Result<>(null, true, null);
  }

  public static <T> Result<T> failure(List<String> errors) {
    return new Result<>(null, false, errors);
  }

  public static <T> Result<T> failure(String errors) {
    return new Result<>(null, false, Collections.singletonList(errors));
  }

  public List<String> getUniqueErrorList() {
    return errors().stream().distinct().toList(); }

  public Set<String> getUniqueErrorSet() {
    return new HashSet<>(errors());
  }

  public boolean isFailure() { return !isSuccess; }
  public boolean isSuccess() { return isSuccess; }
}
