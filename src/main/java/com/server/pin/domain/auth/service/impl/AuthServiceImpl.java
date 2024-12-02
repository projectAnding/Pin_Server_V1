package com.server.pin.domain.auth.service.impl;



import com.server.pin.domain.auth.dto.CheckTeacherApply;
import com.server.pin.domain.auth.dto.request.LoginRequest;
import com.server.pin.domain.auth.dto.request.ReissueRequest;
import com.server.pin.domain.auth.dto.request.UserSignUpRequest;
import com.server.pin.domain.auth.dto.response.UserSignUpResponse;
import com.server.pin.domain.auth.exception.AuthError;
import com.server.pin.domain.auth.repository.RefreshTokenRepository;
import com.server.pin.domain.auth.service.AuthService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.exception.CustomException;
import com.server.pin.global.security.jwt.dto.Jwt;
import com.server.pin.global.security.jwt.enums.JwtType;
import com.server.pin.global.security.jwt.error.JwtError;
import com.server.pin.global.security.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserSignUpResponse teacherSignup(UserSignUpRequest request){

        if (userRepository.existsByUserId(request.userId()) || userRepository.existsByEmail(request.email()) || userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new CustomException(AuthError.USER_ALREADY_EXISTS);
        }

        UserEntity teacher = UserEntity.builder()
                .userId(request.userId())
                .username(request.userName())
                .password(encoder.encode(request.password()))
                .detailDepartment(request.detailDepartment())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .role(UserRole.ROLE_WAITING)
                .build();

        return UserSignUpResponse.of(userRepository.save(teacher));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserSignUpResponse studentSignup(UserSignUpRequest request) {

        if (userRepository.existsByUserId(request.userId()) || userRepository.existsByEmail(request.email()) || userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new CustomException(AuthError.USER_ALREADY_EXISTS);
        } else if(!(request.detailDepartment().matches("\\d{4}"))) {
            throw new CustomException(AuthError.DETAILDEPARTMENT_DATA_WRONG);
        }

        UserEntity student = UserEntity.builder()
                .userId(request.userId())
                .username(request.userName())
                .password(encoder.encode(request.password()))
                .detailDepartment(request.detailDepartment())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .role(UserRole.ROLE_STUDENT)
                .build();



        return UserSignUpResponse.of(userRepository.save(student));
    }

    @Override
    public List<CheckTeacherApply> getTeacherApplies() {
        return userRepository.findAllTeacherApplyByRole(UserRole.ROLE_WAITING);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void teacherSignUpOk(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow( () -> new CustomException(AuthError.ID_NOT_FOUND));
        user.setRole(UserRole.ROLE_TEACHER);

//        userRepository.save(user); // optional?
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void teacherSignUpDeny(Long id) {
        if (!userRepository.existsById(id)) {
            throw new CustomException(AuthError.ID_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Jwt login(LoginRequest request) {
        UserEntity usertemp = userRepository.findByUserId(request.userId());
        if (usertemp == null) {
            throw new CustomException(AuthError.USER_NOT_FOUND);
        }
        UserEntity user = userRepository.findById(usertemp.getId())  // email 대신 userId 사용
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(AuthError.PASSWORD_WRONG);
        }

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(user.getUserId(), token.refreshToken());  // email 대신 userId 사용

        return token;
    }

    @Override
    public Jwt reissue(ReissueRequest request) {
        if (jwtProvider.getType(request.refreshToken()) != JwtType.REFRESH)
            throw new CustomException(JwtError.INVALID_TOKEN);

        String userId = jwtProvider.getUserId(request.refreshToken());  // email 대신 userId 사용

        if (!refreshTokenRepository.existsByUserId(userId))  // email 대신 userId 사용
            throw new CustomException(JwtError.INVALID_TOKEN);

        String refreshToken = refreshTokenRepository.findByUserId(userId)  // email 대신 userId 사용
                .orElseThrow(() -> new CustomException(JwtError.INVALID_TOKEN));

        if (!refreshToken.equals(request.refreshToken()))
            throw new CustomException(JwtError.INVALID_TOKEN);

        UserEntity user = userRepository.findById(userRepository.findByUserId(userId).getId())  // email 대신 userId 사용
                .orElseThrow(() -> new CustomException(AuthError.USER_NOT_FOUND));

        Jwt token = jwtProvider.generateToken(user);

        refreshTokenRepository.save(userId, token.refreshToken());  // email 대신 userId 사용

        return token;
    }

}