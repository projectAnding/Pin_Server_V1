package com.server.pin.domain.email.response.request;


public record SignUpEmailCheckRequest(
        String email,
        Long enterNum
) {
}
