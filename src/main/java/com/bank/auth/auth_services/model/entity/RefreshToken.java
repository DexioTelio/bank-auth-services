package com.bank.auth.auth_services.model.entity;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@RequiredArgsConstructor
public class RefreshToken {
  private final UUID tokenId;
  private final Long authUserId;
  private final String token;
  private final String userAgent;
  private final String ipAddress;
  private final OffsetDateTime issuedAt;
  private final OffsetDateTime expiresAt;
  private final boolean revoked;

  public UUID getTokenId() {return tokenId; }
  public Long getAuthUserId() {return authUserId; }
  public String getToken() {return token; }
  public String getUserAgent() {return userAgent; }
  public String getIpAddress() {return ipAddress; }
  public OffsetDateTime getIssuedAt() {return issuedAt; }
  public OffsetDateTime getExpiresAt() {return expiresAt; }
  public boolean isRevoked() {return revoked; }
}
