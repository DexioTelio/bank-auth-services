package com.bank.auth.auth_services.dto;

public record LoginRequest(
        String username,
        String password
) {
}