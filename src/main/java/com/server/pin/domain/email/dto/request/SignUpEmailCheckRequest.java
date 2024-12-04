package com.server.pin.domain.email.dto.request;


public record SignUpEmailCheckRequest(
        String email,
        Long enterNum
) {
}
