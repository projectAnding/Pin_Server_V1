package com.server.pin.global.security.jwt.provider;

import com.server.pin.domain.auth.repository.RefreshTokenRepository;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.security.jwt.config.JwtProperties;
import com.server.pin.global.security.jwt.dto.Jwt;
import com.server.pin.global.security.jwt.enums.JwtType;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private SecretKey key;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @PostConstruct
    protected void init() {
        key = new SecretKeySpec(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS512.key().build().getAlgorithm()
        );
    }

    public Jwt generateJwt(String userId) {
        Date now = new Date();

        String accessToken = Jwts.builder()
                .header()
                .type(JwtType.ACCESS.name())
                .and()
                .subject(userId)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getAccessTokenExpiration()))
                .signWith(key)
                .compact();

        String refreshToken = Jwts.builder()
                .header()
                .type(JwtType.ACCESS.name())
                .and()
                .subject(userId)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getRefreshTokenExpiration()))
                .signWith(key)
                .compact();

        return new Jwt(accessToken, refreshToken);
    }

}
