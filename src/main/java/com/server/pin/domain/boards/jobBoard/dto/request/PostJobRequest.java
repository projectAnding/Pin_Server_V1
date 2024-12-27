package com.server.pin.domain.boards.jobBoard.dto.request;

import java.time.LocalDate;

public record PostJobRequest(
        String imageUrl,
        String title,
        LocalDate period,
        Long memberLimit,
        String content
) {
}
