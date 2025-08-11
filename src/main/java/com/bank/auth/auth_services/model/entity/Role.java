package com.bank.auth.auth_services.model.entity;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Role {
  private final Long id;
  private final String name;
  private final String description;

  public Long getId() { return id; }
  public String getName() { return name; }
  public String getDescription() { return description; }
}
