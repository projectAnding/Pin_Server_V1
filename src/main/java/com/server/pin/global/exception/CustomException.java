package com.server.pin.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
  private final CustomError error;
}
