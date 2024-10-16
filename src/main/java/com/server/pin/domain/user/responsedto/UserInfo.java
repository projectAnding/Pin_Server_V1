package com.server.pin.domain.user.responsedto;

import lombok.Builder;

@Builder
public record UserInfo(

        String userId,
        String username,

        String detailDepartment, // 학번

        String email,
        String phoneNumber
) {
}
