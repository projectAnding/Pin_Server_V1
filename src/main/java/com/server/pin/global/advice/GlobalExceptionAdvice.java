package com.server.pin.global.advice;

import com.server.pin.global.exception.CustomException;
import com.server.pin.global.exception.ExceptionResponse;
import com.server.pin.global.exception.GlobalError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        return ExceptionResponse.of(e.getError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ExceptionResponse.of(GlobalError.INVALID_INPUT_VALUE);
    }
}