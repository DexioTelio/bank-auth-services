package com.bank.auth.auth_services.repository.interfaces;

import com.bank.auth.auth_services.model.CustomUserDetails;
import io.vavr.control.Try;

public interface AuthUserRepository {
  Try<CustomUserDetails> findByUserName(String username);
}
