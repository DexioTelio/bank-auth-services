package com.bank.auth.auth_services.controller;


import com.bank.auth.auth_services.dto.ErrorResponse;
import com.bank.auth.auth_services.dto.LoginRequest;
import com.bank.auth.auth_services.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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
  public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
    return authService.login(request.username(), request.password())
            .fold(
                    ResponseEntity::ok,
                    failure -> {
                      HttpStatus status = failure.getPrimaryErrorCode()
                              .map(code -> switch (code) {
                                case INVALID_CREDENTIALS, USER_NOT_FOUND -> HttpStatus.UNAUTHORIZED;
                                case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
                                default -> HttpStatus.BAD_REQUEST;
                              })
                              .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

                      ErrorResponse<?> body = ErrorResponse.from(
                              status.value(),
                              status.getReasonPhrase(),
                              failure.getPrimaryErrorCode().get(),
                              failure.getUniqueErrorSet(),
                              httpRequest.getRequestURI()
                      );
                      return ResponseEntity.status(status).body(body);
                    }
            );
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
