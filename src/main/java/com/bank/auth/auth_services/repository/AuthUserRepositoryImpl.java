package com.bank.auth.auth_services.repository;

import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.repository.interfaces.AuthUserRepository;
import io.vavr.control.Try;
import org.springframework.stereotype.Repository;

@Repository
public class AuthUserRepositoryImpl implements AuthUserRepository {
  @Override
  public Try<AuthUser> findByUserName(String username) {
    return null;
  }
}
