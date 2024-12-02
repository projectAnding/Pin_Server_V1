package com.server.pin.domain.boards.boardAuthorMapper.domain.entity;

import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import com.server.pin.domain.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@RequiredArgsConstructor
public class BoardAuthorMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long boardId;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;
}
