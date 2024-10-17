package com.server.pin.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum GlobalExceptionDetail implements ExceptionDetail {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),

    ;

    private final HttpStatus status;
    public final String detail;

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return name();
    }

    @Override
    public String getDetail() {
        return detail;
    }
}
