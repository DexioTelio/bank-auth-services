package com.bank.auth.auth_services.services;

import com.bank.auth.auth_services.dto.RegisterRequest;
import com.bank.auth.auth_services.enums.AuthErrorCode;
import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.repository.AuthUserRepositoryImpl;
import com.bank.auth.auth_services.util.Result;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final AuthUserRepositoryImpl authUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Result<AuthUser> login(String username, String rawPassword) {
    return authUserRepository.findByUserName(username)
            .map(optUser -> optUser
                    .map(user -> {
                      // We verify the password
                      if (bCryptPasswordEncoder.matches(rawPassword, user.getPassword())) {
                        // We update the last login
                        authUserRepository.updateLastLogin(user.getId());
                        return Result.success(user);
                      } else {
                        return Result.<AuthUser>failure(AuthErrorCode.INVALID_CREDENTIALS.getMessage());
                      }
                    })
                    .orElse(Result.<AuthUser>failure(AuthErrorCode.USER_NOT_FOUND.getMessage()))
            )
            .getOrElse(() -> Result.<AuthUser>failure("Unexpected error"));
  }
}