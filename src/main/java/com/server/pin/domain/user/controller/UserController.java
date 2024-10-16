package com.server.pin.domain.user.controller;

import com.server.pin.domain.user.responsedto.UserInfo;
import com.server.pin.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{userId}")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "내 정보 확인하기")
    @GetMapping("/getInfo")
    public UserInfo getStudentInfo(@PathVariable String userId){
        return userService.getStudentInfo(userId);
    }
}
