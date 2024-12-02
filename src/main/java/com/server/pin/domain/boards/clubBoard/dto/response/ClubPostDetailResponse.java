package com.server.pin.domain.boards.clubBoard.dto.response;

import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.boards.enums.PostStatus;
import com.server.pin.domain.user.domain.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClubPostDetailResponse(
        Long id,
        String title,
        UserEntity author,
        LocalDate period,
        Long memberLimit,
        String content,
        String imageURL,
        PostStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ClubPostDetailResponse of(ClubPost post) {
        return new ClubPostDetailResponse(post.getId(), post.getTitle(), post.getAuthor() , post.getPeriod(), post.getMemberLimit(), post.getContent(), post.getImageURL(),post.getStatus() ,post.getCreatedAt(), post.getUpdatedAt());
    }
    //TODO: sadasfsdfsdfds
}
