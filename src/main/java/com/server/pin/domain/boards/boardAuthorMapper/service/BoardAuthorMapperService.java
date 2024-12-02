package com.server.pin.domain.boards.boardAuthorMapper.service;

import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import com.server.pin.domain.user.domain.entity.UserEntity;

public interface BoardAuthorMapperService {
    void saveAuthor(Long boardId, Long authorId, BoardType boardType);
    UserEntity getAuthor(Long boardId, BoardType boardType);
}
