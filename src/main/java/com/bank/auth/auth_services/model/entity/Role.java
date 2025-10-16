package com.bank.auth.auth_services.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Role {
  private final Long id;
  private final String name;
  private final String description;

}
