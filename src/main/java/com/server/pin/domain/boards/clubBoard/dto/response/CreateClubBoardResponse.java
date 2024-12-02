package com.server.pin.domain.boards.clubBoard.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.boards.enums.PostStatus;
import com.server.pin.domain.user.domain.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateClubBoardResponse(
        Long id,
        String title,
        UserEntity author,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate period,
        Long memberLimit,
        String content,
        String imageURL,
        PostStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CreateClubBoardResponse of(ClubPost post) {
        return new CreateClubBoardResponse(post.getId(), post.getTitle(), post.getAuthor(), post.getPeriod(), post.getMemberLimit(), post.getContent(), post.getImageURL(),post.getStatus() ,post.getCreatedAt(), post.getUpdatedAt());
    }
}
