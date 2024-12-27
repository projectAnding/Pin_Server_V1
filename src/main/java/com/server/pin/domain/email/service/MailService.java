package com.server.pin.domain.email.service;

import com.server.pin.domain.email.dto.request.SignUpEmailCheckRequest;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckMailResponse;
import com.server.pin.domain.email.dto.response.SignUpEmailCheckResponse;

public interface MailService {
    SignUpEmailCheckMailResponse sendSignUpEmailCheckMail(String mail);
//    SignUpEmailCheckResponse sendSignUpEmailCheck(SignUpEmailCheckRequest request);
}
