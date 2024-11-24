package com.server.pin.global.security.jwt.provider;

import com.server.pin.domain.auth.repository.RefreshTokenRepository;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.security.jwt.config.JwtProperties;
import com.server.pin.global.security.jwt.dto.Jwt;
import com.server.pin.global.security.jwt.enums.JwtType;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private SecretKey key;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        key = new SecretKeySpec(
                jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS512.key().build().getAlgorithm()
        );
    }

    public String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public JwtType getType(String token) {
        return JwtType.valueOf(Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getHeader()
                .getType()
        );
    }

    public Jwt generateToken(UserEntity user) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        return new Jwt(accessToken, refreshToken);
    }

    public Authentication getAuthentication(String token) {
        String userId = getUserId(token);
        UserDetails details = userDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }

    private String generateAccessToken(UserEntity user) {
        Date now = new Date();

        return Jwts.builder()
                .header()
                .type(JwtType.ACCESS.name())
                .and()
                .subject(user.getUserId()) // 이메일 대신 userId 사용
                .signWith(key)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getAccessTokenExpiration()))
                .compact();
    }

    private String generateRefreshToken(UserEntity user) {
        Date now = new Date();

        return Jwts.builder()
                .header()
                .type(JwtType.REFRESH.name())
                .and()
                .subject(user.getUserId()) // 이메일 대신 userId 사용
                .signWith(key)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtProperties.getRefreshTokenExpiration()))
                .compact();
    }

}
