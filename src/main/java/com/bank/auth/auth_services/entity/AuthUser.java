package com.bank.auth.auth_services.entity;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Builder
public class AuthUser {
  private final Long id;
  private final Long externalUserId;
  private final String username;
  private final String email;
  private final String password;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean emailVerified;
  private final boolean twoFactorEnable;
  private final OffsetDateTime lastLoginAt;
  private final OffsetDateTime lastPasswordChangeAt;
  private final int userTypeId;
  private final String status;

  public Long getId() { return id; }
  public Long getExternalUserId() { return externalUserId; }
  public String getUsername() { return username; }
  public String getEmail() { return email; }
  public String getPassword() { return password; }
  public boolean isAccountNonLocked() { return accountNonLocked; }
  public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
  public boolean isEmailVerified() { return emailVerified; }
  public boolean isTwoFactorEnable() { return twoFactorEnable; }
  public OffsetDateTime getLastLoginAt() { return lastLoginAt; }
  public OffsetDateTime getLastPasswordChangeAt() { return lastPasswordChangeAt; }
  public int getUserTypeId() { return userTypeId; }
  public String getStatus() { return status; }
}
