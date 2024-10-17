package com.server.pin.global.response;

import com.server.pin.global.exception.CustomException;
import com.server.pin.global.exception.ExceptionDetail;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseError extends Response {
    private final String detail;

    public ResponseError(String message, String detail) {
        super(false, message);
        this.detail = detail;
    }

    public static ResponseEntity<ResponseError> of(ExceptionDetail details) {
        return ResponseEntity.status(details.getHttpStatus()).body(ofBody(details));
    }

    public static ResponseError ofBody(ExceptionDetail details) {
        return new ResponseError(details.getMessage(), details.getDetail());
    }
}
