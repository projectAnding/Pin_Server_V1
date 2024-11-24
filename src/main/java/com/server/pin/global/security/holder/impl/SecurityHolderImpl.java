package com.server.pin.global.security.holder.impl;


import com.server.pin.domain.auth.exception.AuthError;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.exception.CustomException;
import com.server.pin.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityHolderImpl implements SecurityHolder {
    private final UserRepository userRepository;

    @Override
    public UserEntity getPrincipal() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return Optional.of( userRepository.findByUserId(userId) ).orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));
    }
}
