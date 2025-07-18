package com.bank.auth.auth_services.model.entity;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@RequiredArgsConstructor
public class LoginAttempt {
  private final Long id;
  private final Long authUserId;
  private final String usernameOrEmail;
  private final String ipAddress;
  private final String userAgent;
  private final boolean successful;
  private final OffsetDateTime attemptedAt;

  public Long getId() { return id; }
  public Long getAuthUserId() { return authUserId; }
  public String getUsernameOrEmail() { return usernameOrEmail; }
  public String getIpAddress() { return ipAddress; }
  public String getUserAgent() { return userAgent; }
  public boolean isSuccessful() { return successful; }
  public OffsetDateTime getAttemptedAt() { return attemptedAt; }
}
