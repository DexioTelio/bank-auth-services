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

  public Result<Void> register(RegisterRequest request) {
   if (authUserRepository.existByUsername(request.username())) {
     return Result.failure(AuthErrorCode.USERNAME_ALREADY_EXISTS.getMessage());
    }

    String hashedPassword = bCryptPasswordEncoder.encode(request.password());

   Try<AuthUser> user = authUserRepository.save(request.username(), request.email(), hashedPassword);
    return Result.success();

  }
}