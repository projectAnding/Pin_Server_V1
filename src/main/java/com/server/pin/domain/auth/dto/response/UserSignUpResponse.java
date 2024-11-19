package com.server.pin.domain.auth.dto.response;

import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;

public record UserSignUpResponse(
        Long id,
        String userId,
        String userName,
        String password,
        String detailDepartment,
        String email,
        String phoneNumber,
        UserRole roll
) {
    public static UserSignUpResponse of(UserEntity user) {
        return new UserSignUpResponse(user.getId(), user.getUserId(), user.getUsername(), user.getPassword(), user.getDetailDepartment(), user.getEmail(), user.getPhoneNumber(), user.getRole());
    }
}
