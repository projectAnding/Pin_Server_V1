package com.server.pin.domain.auth.service;

import com.server.pin.domain.auth.dto.CheckTeacherApply;
import com.server.pin.domain.auth.dto.request.LoginRequest;
import com.server.pin.domain.auth.dto.request.UserSignUpRequest;
import com.server.pin.domain.auth.dto.response.UserSignUpResponse;
import com.server.pin.global.security.jwt.dto.Jwt;

import java.util.List;

public interface AuthService {
    //singUp
    UserSignUpResponse teacherSignup(UserSignUpRequest request);
    UserSignUpResponse studentSignup(UserSignUpRequest request);
    void teacherSignUpOk(Long id);
    void teacherSignUpDeny(Long id);
    //login
    Jwt login(LoginRequest request);
    //teacherMyPage
    List<CheckTeacherApply> getTeacherApplies();
}
