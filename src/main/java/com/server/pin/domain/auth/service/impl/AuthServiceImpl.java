package com.server.pin.domain.auth.service.impl;

import com.server.pin.domain.auth.dto.CheckTeacherApply;
import com.server.pin.domain.auth.dto.request.LoginRequest;
import com.server.pin.domain.auth.exception.impl.DetailDepartmentDataTypeWrongException;
import com.server.pin.domain.auth.exception.impl.IdNotFoundException;
import com.server.pin.domain.auth.exception.impl.PasswordWrong;
import com.server.pin.domain.auth.exception.impl.UserAlreadyExistsException;
import com.server.pin.domain.auth.dto.request.UserSignUpRequest;
import com.server.pin.domain.auth.service.AuthService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.security.jwt.dto.Jwt;
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void teacherSignup(UserSignUpRequest request){

        if (userRepository.existsByUserId(request.userId()) || userRepository.existsByEmail(request.email()) || userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw UserAlreadyExistsException.INSTANCE;
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

        userRepository.save(teacher);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void studentSignup(UserSignUpRequest request) {

        if (userRepository.existsByUserId(request.userId()) || userRepository.existsByEmail(request.email()) || userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw UserAlreadyExistsException.INSTANCE;
        } else if(!(request.detailDepartment().matches("\\d{4}"))) {
            throw DetailDepartmentDataTypeWrongException.INSTANCE;
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



        userRepository.save(student);
    }

    @Override
    public List<CheckTeacherApply> getTeacherApplies() {
        return userRepository.findAllTeacherApplyByRole(UserRole.ROLE_WAITING);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void teacherSignUpOk(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> IdNotFoundException.INSTANCE);
        user.setRole(UserRole.ROLE_TEACHER);

//        userRepository.save(user); // optional?
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void teacherSignUpDeny(Long id) {
        if (!userRepository.existsById(id)) {
            throw IdNotFoundException.INSTANCE;
        }
        userRepository.deleteById(id);
    }

    @Override
    public Jwt login(LoginRequest request) {
        String userId = request.userId();
        String password = request.password();

        if (!userRepository.existsByUserId(userId)) {
            throw IdNotFoundException.INSTANCE;
        }

        UserEntity user = userRepository.findByUserId(userId);

        if (!encoder.matches(password, user.getPassword())) {
            throw PasswordWrong.INSTANCE;
        }

        return null;
    }
}