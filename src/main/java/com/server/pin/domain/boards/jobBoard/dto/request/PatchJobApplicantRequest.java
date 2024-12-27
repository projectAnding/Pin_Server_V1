package com.server.pin.domain.boards.jobBoard.dto.request;

public record PatchJobApplicantRequest(
        Long applicantId,
        Long postId
) {
}
