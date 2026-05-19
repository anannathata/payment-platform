package com.nana.payments.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nana.payments.auth.dto.LoginRequest;

@ExtendWith(MockitoExtension.class)

public class AuthServiceTest {

    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthService authService;

    @Test
    void shouldReturnLoginResponseWhenCredentialsAreValid() {

        when(jwtService.generateToken("admin")).thenReturn("fake-token");
        when(jwtService.getExpiration()).thenReturn(3600000L);

        var response = authService.login(new LoginRequest("admin", "admin123"));

        assertEquals("fake-token", response.accessToken());
        assertEquals("Bearer", response.tokenType());
        assertEquals(3600000L, response.expiresIn());

    }

    @Test
    void shouldThrowExceptionWhenCredentialsAreInvalid() {
        LoginRequest request = new LoginRequest("admin", "wrong-password");

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }
}