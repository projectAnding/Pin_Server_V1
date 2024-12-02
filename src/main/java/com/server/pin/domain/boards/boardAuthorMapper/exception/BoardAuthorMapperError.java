package com.server.pin.domain.boards.boardAuthorMapper.exception;

import com.server.pin.global.exception.CustomError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardAuthorMapperError implements CustomError {
    MAPPING_FAILED(HttpStatus.BAD_REQUEST, "board author mapping failed. go to server developer")
    ;

    private final HttpStatus status;
    private final String message;
}
