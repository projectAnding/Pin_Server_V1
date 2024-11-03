package com.server.pin.domain.admin.controller;

import com.server.pin.domain.admin.service.AdminService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

}
