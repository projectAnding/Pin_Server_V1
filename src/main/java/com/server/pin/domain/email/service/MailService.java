package com.server.pin.domain.email.service;

import com.server.pin.domain.email.response.request.SignUpEmailCheckRequest;
import com.server.pin.domain.email.response.response.SignUpEmailCheckMailResponse;
import com.server.pin.domain.email.response.response.SignUpEmailCheckResponse;

public interface MailService {
    SignUpEmailCheckMailResponse sendSignUpEmailCheckMail(String mail);
    SignUpEmailCheckResponse sendSignUpEmailCheck(SignUpEmailCheckRequest request);
}
