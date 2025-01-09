package com.bank.auth.auth_services.controller;


import com.bank.auth.auth_services.dto.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
  private final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    return null;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody LoginRequest request) {
    return null;
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
