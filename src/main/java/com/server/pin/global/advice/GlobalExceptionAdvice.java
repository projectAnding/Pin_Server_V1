package com.server.pin.global.advice;

import com.server.pin.global.exception.CustomException;
import com.server.pin.global.response.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseError> handleCustomException(CustomException exception) {
        return ResponseError.of(exception.getDetails());
    }
}
