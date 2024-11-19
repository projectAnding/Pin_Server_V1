package com.server.pin.domain.admin.controller;

import com.server.pin.domain.admin.service.AdminService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "관리자 API", description = "관리자용 API")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/student/list")
    @Operation(description = "사용자 명단 조회")
    public List<UserEntity> userList() {
        return adminService.userList();
    }

    @DeleteMapping("/user/delete/all")
    @Operation(description = "나 말곤 건들지 말 것.")
    public ResponseEntity<BaseResponse<Void>> deleteAll() {
        return BaseResponse.of(null, 200, "사용자 전체 삭제 성공");
    }
}
