package com.server.pin.domain.user.responsedto;

import com.server.pin.domain.user.domain.entity.UserEntity;
import lombok.Builder;

@Builder
public record UserInfo(

        String profileImageURL,
        String userId,
        String username,

        String detailDepartment, // 학번

        String email,
        String phoneNumber
) {
    public static UserInfo of(UserEntity user) {
        return new UserInfo(user.getProfileImageURL(), user.getUserId(), user.getUsername(),user.getDetailDepartment(),user.getEmail(),user.getPhoneNumber());
    }
}
