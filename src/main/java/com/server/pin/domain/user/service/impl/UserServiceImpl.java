package com.server.pin.domain.user.service.impl;

import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.domain.user.responsedto.UserInfo;
import com.server.pin.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo getStudentInfo(String userId) {

        UserEntity userEntityOptional = userRepository.findByUserId(userId);

        return UserInfo.of(userEntityOptional);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }
}
