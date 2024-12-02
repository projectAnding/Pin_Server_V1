package com.server.pin.domain.file.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.server.pin.domain.file.dto.request.UploadImageRequest;
import com.server.pin.domain.file.exception.FileError;
import com.server.pin.domain.file.service.FileService;
import com.server.pin.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;


    public String uploadFile(UploadImageRequest request) {
        return fileService.uploadFile(request);
    }
}
