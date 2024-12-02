package com.server.pin.domain.boards.clubBoard.repository;

import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostResponse;
import com.server.pin.domain.boards.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubPostRepository extends JpaRepository<ClubPost, Long> {
    List<ClubPost> findAllByStatus(PostStatus status);
}
