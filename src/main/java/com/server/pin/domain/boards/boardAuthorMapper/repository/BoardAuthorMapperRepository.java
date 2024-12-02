package com.server.pin.domain.boards.boardAuthorMapper.repository;

import com.server.pin.domain.boards.boardAuthorMapper.domain.entity.BoardAuthorMapper;
import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardAuthorMapperRepository extends JpaRepository<BoardAuthorMapper, Long> {
    BoardAuthorMapper findByBoardIdAndBoardType(Long boardId, BoardType boardType);
}
