package com.server.pin.domain.boards.clubBoard.domain.entity;

import com.server.pin.domain.boards.enums.PostStatus;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.global.common.time.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubposts")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ClubPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    private LocalDate period;

    private Long memberLimit;

    @NotBlank
    private String content;

    private String imageURL;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status;
}