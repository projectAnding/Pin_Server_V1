package com.server.pin.domain.auth.exception.impl;

import com.server.pin.domain.auth.exception.AuthExceptionDetails;
import com.server.pin.global.exception.CustomException;

public class IdNotFoundException extends CustomException {
    private IdNotFoundException() {super(AuthExceptionDetails.ID_NOT_FOUND);}

    public static final IdNotFoundException INSTANCE = new IdNotFoundException();
}
