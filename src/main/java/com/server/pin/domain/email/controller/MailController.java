package com.server.pin.domain.email.controller;

import com.server.pin.domain.email.service.MailService;
import com.server.pin.domain.email.service.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    private int number;

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public String mailSend(String mail) {
        return mailService.sendSignUpEmailCheckMail(mail);
    }

    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

        boolean isMatch = userNumber.equals(String.valueOf(number));

        return ResponseEntity.ok(isMatch);
    }
}