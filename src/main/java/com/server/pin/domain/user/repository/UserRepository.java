package com.server.pin.domain.user.repository;

import com.server.pin.domain.auth.dto.CheckTeacherApply;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.domain.enums.UserRole;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserId(String userId);
    UserEntity findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    List<CheckTeacherApply> findAllTeacherApplyByRole(UserRole role);

    @Query("SELECT u FROM UserEntity u WHERE u.role <> :excludedRole")
    List<UserEntity> findUsersWithoutRole(@Param("excludedRole") UserRole excludedRole);

}
