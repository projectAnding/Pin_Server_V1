package com.server.pin.domain.boards.clubBoard.dto.response;

import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.user.domain.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public record ClubPostResponse(
        Long id,
        String imageUrl,
        String title,
        Long memberLimit,
        UserEntity author,
        LocalDateTime createdAt

        //TODO: 나중에 손봅시다

) {
    public static ClubPostResponse of(ClubPost post) {
        return new ClubPostResponse(post.getId(), post.getTitle(), post.getImageURL() ,post.getMemberLimit(), post.getAuthor(), post.getCreatedAt());
    }
}
