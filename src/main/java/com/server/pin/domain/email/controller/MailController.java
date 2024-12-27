package com.server.pin.domain.email.controller;

import com.server.pin.domain.email.dto.request.MailSendRequest;
import com.server.pin.domain.email.dto.request.SignUpEmailCheckRequest;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckResponse;
import com.server.pin.domain.email.service.MailService;
import com.server.pin.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "이메일 API", description = "Gmail 연락")
public class MailController {
    private final MailService mailService;
    private int number;

    
    @PostMapping("/mailSend")
    @Operation(summary = "인증 이메일 전송")
    public ResponseEntity<BaseResponse<SignUpEmailCheckMailResponse>> mailSend(@RequestBody MailSendRequest request) {
        return BaseResponse.of(mailService.sendSignUpEmailCheckMail(request.email()), 200, "인증이메일 전송 완료");
    }

//    @PostMapping("/mailCheck")
//    public ResponseEntity<BaseResponse<SignUpEmailCheckResponse>> mailCheck(@RequestBody SignUpEmailCheckRequest request) {
//        return BaseResponse.of(mailService.sendSignUpEmailCheck(request), 200, "이메일 확인 완료") ;
//    }
}