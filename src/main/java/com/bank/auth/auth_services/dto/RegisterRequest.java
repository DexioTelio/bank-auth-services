package com.bank.auth.auth_services.dto;

public record RegisterRequest(
        Long externalIUserId,
        String username,
        String password,
        String email
) {
}
