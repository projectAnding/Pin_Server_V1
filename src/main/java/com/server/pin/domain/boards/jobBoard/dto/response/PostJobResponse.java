package com.server.pin.domain.boards.jobBoard.dto.response;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPost;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.responsedto.UserResponse;

import java.time.LocalDate;

public record PostJobResponse(
        Long id,
        String imageUrl,
        String title,
        UserResponse author,
        LocalDate period,
        Long nowMember,
        Long memberLimit,
        String content
) {
    public static PostJobResponse of(JobPost post) {
        return new PostJobResponse(post.getId(), post.getImageUrl(), post.getTitle(), UserResponse.of(post.getAuthor()), post.getPeriod(), post.nowMember(), post.getMemberLimit(), post.getContent());
    }
}
