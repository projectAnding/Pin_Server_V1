package com.server.pin.domain.auth.exception.impl;

import com.server.pin.domain.auth.exception.AuthExceptionDetails;
import com.server.pin.global.exception.CustomException;

public class UserAlreadyExistsException extends CustomException {
    private UserAlreadyExistsException() {
        super(AuthExceptionDetails.USER_ALREADY_EXISTS);
    }

    public static final UserAlreadyExistsException INSTANCE = new UserAlreadyExistsException();
}
