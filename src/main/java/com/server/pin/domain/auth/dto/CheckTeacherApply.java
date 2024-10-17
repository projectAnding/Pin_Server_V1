package com.server.pin.domain.auth.dto;

public record CheckTeacherApply(

        Long id,

        String userId,
        String username,

        String detailDepartment, // 학번

        String email,
        String phoneNumber
) {
}
