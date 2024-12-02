package com.server.pin.domain.user.domain.entity;

import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.user.domain.enums.UserRole;
import com.server.pin.global.common.time.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false, name = "userid")
    String userId;

    @Column(nullable = false, name = "username")
    String username;

    @Column(nullable = false, name = "password")
    String password;

    @Column(unique = false, name = "detaildepartment", nullable = false)
    String detailDepartment; // 학번

    @Column(unique = true, name = "email", nullable = false)
    @Email(message = "email wrong")
    public String email;

    @Column(unique = true, name = "phonenumber", nullable = false)
    public String phoneNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    public UserRole role;

}
