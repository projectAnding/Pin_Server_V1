package com.server.pin.domain.user.service;

import com.server.pin.domain.user.responsedto.UserInfo;

public interface UserService {
    UserInfo getStudentInfo();
    boolean existsByUserId(String userId);
}
