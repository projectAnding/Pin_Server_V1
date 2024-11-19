package com.server.pin.domain.auth.exception;

import com.server.pin.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@RequiredArgsConstructor
public enum AuthError implements CustomError {
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "user already exists"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "user not found"),
    DETAILDEPARTMENT_DATA_WRONG(HttpStatus.BAD_REQUEST, "detaildepartment data type wrong"),
    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "id not found in database"),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "password wrong"),
    ;

    private final HttpStatus status;
    private final String message;
}
