package com.bank.auth.auth_services.model.entity;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@RequiredArgsConstructor
public class UserRole {
  private final Long Id;
  private final Long roleId;
  private final OffsetDateTime assignedAt;
  private final Long assignedBy;

  public Long getId() { return Id; }
  public Long getRoleId() { return roleId; }
  public OffsetDateTime getAssignedAt() { return assignedAt; }
  public Long getAssignedBy() { return assignedBy; }
}
