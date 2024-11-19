package com.server.pin.domain.admin.service.impl;

import com.server.pin.domain.admin.service.AdminService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;
import com.server.pin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    @Override
    public List<UserEntity> userList() {
        return userRepository.findUsersWithoutRole(UserRole.ROLE_WAITING);
    }

    @Override
    public Void deleteAllUsers() {
        userRepository.deleteAll();
        return null;
    }

}
