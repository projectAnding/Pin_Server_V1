package com.server.pin.domain.auth.exception.impl;

import com.server.pin.domain.auth.exception.AuthExceptionDetails;
import com.server.pin.global.exception.CustomException;

public class UserNotFoundException extends CustomException {
    private UserNotFoundException() {super(AuthExceptionDetails.USER_NOT_FOUND);}

    public static final UserNotFoundException INSTANCE = new UserNotFoundException();
}
