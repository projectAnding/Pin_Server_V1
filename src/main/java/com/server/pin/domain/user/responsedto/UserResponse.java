package com.server.pin.domain.user.responsedto;

import com.server.pin.domain.user.domain.entity.UserEntity;

public record UserResponse(
        String profileImageURL,
        String userId,
        String username,
        String detailDepartment
) {
    public static UserResponse of(UserEntity user) {
        return new UserResponse(user.getProfileImageURL(), user.getUserId(), user.getUsername(), user.getDetailDepartment());
    }
}
