package com.server.pin.global.security.holder;

import com.server.pin.domain.user.domain.entity.UserEntity;

public interface SecurityHolder {
    UserEntity getPrincipal();
}
