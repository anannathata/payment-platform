package com.nana.payments.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.junit.jupiter.api.Test;

public class JwtServiceTest {

    JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "uma-chave-grande-o-suficiente-para-hmac");
        ReflectionTestUtils.setField(jwtService, "expiration", 3600000L);

    }

    @Test
    void shouldGenerateTokenWithUsernameAsSubject() {

        String token = jwtService.generateToken("admin");
        SecretKey key = Keys.hmacShaKeyFor("uma-chave-grande-o-suficiente-para-hmac".getBytes(StandardCharsets.UTF_8));

        assertTrue(token.length() > 0);

        Jws<Claims> jws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        String username = jws.getPayload().getSubject();

        assertEquals("admin", username);

    }
}
