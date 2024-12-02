package com.server.pin.domain.file.service;

import com.server.pin.domain.file.dto.request.UploadImageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(UploadImageRequest request);
}
