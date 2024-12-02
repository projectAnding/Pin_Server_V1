package com.server.pin.domain.boards.boardAuthorMapper.service.impl;

import com.server.pin.domain.boards.boardAuthorMapper.domain.entity.BoardAuthorMapper;
import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import com.server.pin.domain.boards.boardAuthorMapper.exception.BoardAuthorMapperError;
import com.server.pin.domain.boards.boardAuthorMapper.repository.BoardAuthorMapperRepository;
import com.server.pin.domain.boards.boardAuthorMapper.service.BoardAuthorMapperService;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.exception.CustomError;
import com.server.pin.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardAuthorMapperServiceImpl implements BoardAuthorMapperService {
    private final BoardAuthorMapperRepository boardAuthorMapperRepository;
    private final UserRepository userRepository;

    @Override
    public void saveAuthor(Long boardId, Long authorId, BoardType boardType) {
        BoardAuthorMapper boardAuthorMapper = BoardAuthorMapper.builder()
                        .boardId(boardId)
                        .userId(authorId)
                        .build();

        try {
            boardAuthorMapperRepository.save(boardAuthorMapper);
        } catch (Exception e) {
            throw new CustomException(BoardAuthorMapperError.MAPPING_FAILED);
        }
    }

    @Override
    public UserEntity getAuthor(Long boardId, BoardType boardType) {
        return userRepository.findById(boardAuthorMapperRepository.findByBoardIdAndBoardType(boardId, boardType).getUserId()).get();
    }
}
