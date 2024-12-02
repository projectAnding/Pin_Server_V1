package com.server.pin.domain.boards.clubBoard.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CreateClubBoardRequest(
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate period,
        Long memberLimit,
        String content
) {
}
