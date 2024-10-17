package com.server.pin.domain.auth.dto.request;

public record LoginRequest(
        String userId,
        String password
){
}
