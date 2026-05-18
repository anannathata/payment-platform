package com.nana.payments.auth.service;

import org.springframework.stereotype.Service;

import com.nana.payments.auth.dto.LoginRequest;
import com.nana.payments.auth.dto.LoginResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        if (!request.username().equals("admin") || !request.password().equals("admin123")) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(request.username());

        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpiration());
    }
}