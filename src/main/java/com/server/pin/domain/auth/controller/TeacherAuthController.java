package com.server.pin.domain.auth.controller;

import com.server.pin.domain.auth.dto.CheckTeacherApply;
import com.server.pin.domain.auth.service.AuthService;
import com.server.pin.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/teacher")
@RequiredArgsConstructor
@Tag(name = "교사 Auth", description = "교사인증 API")
public class TeacherAuthController {
    private final AuthService authService;


    @Operation(summary = "교사 회원가입 신청자 명단 확인")
    @GetMapping("/applies")
    public List<CheckTeacherApply> teacherApply() {
        return authService.getTeacherApplies();
    }

    @Operation(summary = "교사 회원가입 신청 거절", description = "거절할 대상의 Id 입력")
    @DeleteMapping("/apply/deny")
    public ResponseEntity<Response> teacherApplyDeny(@RequestBody Long id) {
        authService.teacherSignUpDeny(id);

        return Response.ok("DENY SUCCESS");
    }

    @Operation(summary = "교사 회원가입 신청 승인")
    @PatchMapping("/apply/ok")
    public ResponseEntity<Response> teacherApplyOk(@RequestBody Long id) {
        authService.teacherSignUpOk(id);

        return Response.ok("Success");
    }

}
