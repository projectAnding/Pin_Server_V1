package com.server.pin.domain.email.dto.response;

import com.server.pin.domain.email.domain.entity.AuthenticationNumber;

public record SignUpEmailCheckMailResponse(
        String email,
        Long number
) {
    public static SignUpEmailCheckMailResponse of(AuthenticationNumber authNum) {
        return new SignUpEmailCheckMailResponse(authNum.getEmail(), authNum.getNumber());
    }
}
