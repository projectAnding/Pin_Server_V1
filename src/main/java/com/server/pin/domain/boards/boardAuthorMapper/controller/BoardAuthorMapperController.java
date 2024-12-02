package com.server.pin.domain.boards.boardAuthorMapper.controller;

import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import com.server.pin.domain.boards.boardAuthorMapper.service.BoardAuthorMapperService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardAuthorMapperController {
    private final BoardAuthorMapperService boardAuthorMapperService;

    public void postAuthorMap(Long boardId, Long authorId, BoardType boardType) {
        boardAuthorMapperService.saveAuthor(boardId, authorId, boardType);
    }

    public UserEntity getAuthor(Long boardId, BoardType boardType) {
        return boardAuthorMapperService.getAuthor(boardId, boardType);
    }

}
