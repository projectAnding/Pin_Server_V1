package com.server.pin.domain.file.exception;

import com.server.pin.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FileError implements CustomError {
    FILE_CREATE_FAIL(HttpStatus.BAD_REQUEST, "failed to create file")
    ;

    private final HttpStatus status;
    private final String message;
}
