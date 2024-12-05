package com.server.pin.domain.email.controller;

import com.server.pin.domain.email.dto.request.MailSendRequest;
import com.server.pin.domain.email.dto.request.SignUpEmailCheckRequest;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckResponse;
import com.server.pin.domain.email.service.MailService;
import com.server.pin.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    private int number;

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public ResponseEntity<BaseResponse<SignUpEmailCheckMailResponse>> mailSend(@RequestBody MailSendRequest request) {
        return BaseResponse.of(mailService.sendSignUpEmailCheckMail(request.email()), 200, "인증이메일 전송 완료");
    }

    @PostMapping("/mailCheck")
    public ResponseEntity<BaseResponse<SignUpEmailCheckResponse>> mailCheck(@RequestBody SignUpEmailCheckRequest request) {
        return BaseResponse.of(mailService.sendSignUpEmailCheck(request), 200, "이메일 확인 완료") ;
    }
}