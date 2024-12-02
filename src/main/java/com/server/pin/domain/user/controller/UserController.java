package com.server.pin.domain.user.controller;

import com.server.pin.domain.user.responsedto.UserInfo;
import com.server.pin.domain.user.service.UserService;
import com.server.pin.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "내 정보 확인하기")
    @GetMapping("/getMyInfo")
    public ResponseEntity<BaseResponse<UserInfo>> getStudentInfo(){
        return BaseResponse.of(userService.getStudentInfo(), 200, "회원정보 불러오기 성공");
    }
}
