package com.bank.auth.auth_services.repository.interfaces;

import com.bank.auth.auth_services.model.entity.Role;

import java.util.List;

public interface RoleRepository {
  List<Role> findRoleByUserId(Long userId);
}
