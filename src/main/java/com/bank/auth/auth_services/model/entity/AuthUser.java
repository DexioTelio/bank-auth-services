package com.bank.auth.auth_services.model.entity;

import lombok.*;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class AuthUser {
  private final Long id;
  private final Long externalUserId;
  private final String username;
  private final String email;
  private final String password;
  private final boolean accountNonLocked;
  private final boolean isAccountNonExpired;
  private final boolean credentialsNonExpired;
  private final boolean emailVerified;
  private final boolean twoFactorEnable;
  private final OffsetDateTime lastLoginAt;
  private final OffsetDateTime lastPasswordChangeAt;
  private final int userTypeId;
  private final String status;
}
