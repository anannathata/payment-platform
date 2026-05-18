package com.nana.payments.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long expiresIn) {
}