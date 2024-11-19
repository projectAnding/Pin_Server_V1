package com.server.pin.domain.admin.service;

import com.server.pin.domain.user.domain.entity.UserEntity;

import java.util.List;

public interface AdminService {
    List<UserEntity> userList();
    Void deleteAllUsers();
}
