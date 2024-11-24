package com.server.pin.domain.auth.repository;

import java.util.Optional;

public interface RefreshTokenRepository {
    void save(String userId, String refreshToken);  // email -> userId

    Optional<String> findByUserId(String userId);  // email -> userId

    void deleteByUserId(String userId);  // email -> userId

    boolean existsByUserId(String userId);  // email -> userId
}
