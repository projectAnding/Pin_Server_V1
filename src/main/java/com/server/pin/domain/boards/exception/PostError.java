package com.server.pin.domain.boards.exception;

import com.server.pin.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostError implements CustomError {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "cannot find post"),
    POST_AUTHOR_MAP_FAILED(HttpStatus.BAD_REQUEST, "cannot map post to author"),
    ;

    private final HttpStatus status;
    private final String message;
}

