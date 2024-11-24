package com.server.pin.domain.auth.repository.impl;

import com.server.pin.domain.auth.repository.RefreshTokenRepository;
import com.server.pin.global.security.jwt.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final JwtProperties jwtProperties;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String userId, String refreshToken) {
        redisTemplate.opsForValue().set("refreshToken:" + userId, refreshToken, jwtProperties.getRefreshTokenExpiration(), TimeUnit.MILLISECONDS);  // email -> userId
    }

    @Override
    public Optional<String> findByUserId(String userId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get("refreshToken:" + userId));  // email -> userId
    }

    @Override
    public void deleteByUserId(String userId) {
        redisTemplate.delete("refreshToken:" + userId);  // email -> userId
    }

    @Override
    public boolean existsByUserId(String userId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("refreshToken:" + userId));  // email -> userId
    }
}
