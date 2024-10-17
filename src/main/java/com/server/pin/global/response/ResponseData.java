package com.server.pin.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseData<T> extends Response {
    private final T data;

    public ResponseData(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseData<T>> of(HttpStatus status, String message, T data) {
        return ResponseEntity.status(status).body(new ResponseData<>(status.is2xxSuccessful(), message, data));
    }

    public static <T> ResponseEntity<ResponseData<T>> ok(String message, T data) {
        return of(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<ResponseData<T>> ok(T data) {
        return ok("Success", data);
    }
}
