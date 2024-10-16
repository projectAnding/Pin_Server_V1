package com.server.pin.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class Response {
    private final boolean success;
    private final String message;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ResponseEntity<Response> of(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new Response(status.is2xxSuccessful(), message));
    }

    public static ResponseEntity<Response> ok(String message) {
        return of(HttpStatus.OK, message);
    }

    public static ResponseEntity<Response> ok() {
        return ok("success");
    }
}
