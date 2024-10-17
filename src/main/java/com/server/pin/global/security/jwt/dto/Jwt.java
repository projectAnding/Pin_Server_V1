package com.server.pin.global.security.jwt.dto;

public record Jwt(
        String accessToken,
        String refreshToken
) {
}
