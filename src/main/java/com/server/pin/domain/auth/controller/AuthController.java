package com.server.pin.domain.auth.controller;

import com.server.pin.domain.auth.dto.request.LoginRequest;
import com.server.pin.domain.auth.dto.request.UserSignUpRequest;
import com.server.pin.domain.auth.dto.response.UserSignUpResponse;
import com.server.pin.domain.auth.service.AuthService;
import com.server.pin.global.response.BaseResponse;
import com.server.pin.global.security.jwt.dto.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // TODO: 신청 허가 페이지로 보내기
    @Tag(name = "교사 Auth", description = "교사인증 API")
    @Operation(summary = "교사 회원가입")
    @PostMapping("/signup/teacher")
    public ResponseEntity<BaseResponse<UserSignUpResponse>> teacherSingUp(@RequestBody UserSignUpRequest request) {
        return BaseResponse.of(authService.teacherSignup(request), 200, "교사 회원가입 성공");
    }

    @Tag(name = "학생 Auth", description = "학생인증 API")
    @Operation(summary = "학생 회원가입")
    @PostMapping("/signup/student")
    public ResponseEntity<BaseResponse<UserSignUpResponse>> studentSingUp(@RequestBody UserSignUpRequest request) {

        return BaseResponse.of(authService.studentSignup(request), 200, "학생 회원가입 성공");
    }

    // TODO: 로그인 기능
    @Tag(name = "로그인 Auth", description = "로그인 API")
    @Operation(summary = "로그인", description = "사용금지")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Jwt>> login(@RequestBody LoginRequest request) {
        return BaseResponse.of(authService.login(request), 200, "로그인 성공");
    }

    // TODO: 리이슈 기능
}
