package com.bank.auth.auth_services.controller;


import com.bank.auth.auth_services.dto.ErrorResponse;
import com.bank.auth.auth_services.dto.LoginRequest;
import com.bank.auth.auth_services.enums.AuthErrorCode;
import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.services.AuthService;
import com.bank.auth.auth_services.util.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final Logger logger = LoggerFactory.getLogger(AuthController.class);
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Result<AuthUser> result = authService.login(request.username(), request.password());

    if (result.isSuccess()) {
      return ResponseEntity.ok(result.value());
    } else {
      return ResponseEntity
              .status(HttpStatus.CONFLICT)
              .body(new ErrorResponse<>(
                      409,
                      AuthErrorCode.USERNAME_ALREADY_EXISTS,
                      result.getUniqueErrorSet()
              ));
    }
  }

  @PostMapping("/roles")
  public ResponseEntity<?> roles(@RequestBody LoginRequest request) {
    return null;
  }

  @PostMapping("/validate")
  public ResponseEntity<?> validate(@RequestBody LoginRequest request) {
    return null;
  }
}
