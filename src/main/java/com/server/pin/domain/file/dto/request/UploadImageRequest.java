package com.server.pin.domain.file.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageRequest(
        Long postId,
        MultipartFile file
) {
}
