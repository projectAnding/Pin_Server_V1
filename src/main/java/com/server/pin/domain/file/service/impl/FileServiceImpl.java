package com.server.pin.domain.file.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.server.pin.domain.file.dto.request.UploadImageRequest;
import com.server.pin.domain.file.exception.FileError;
import com.server.pin.domain.file.service.FileService;
import com.server.pin.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadFile(UploadImageRequest request) {
        try {
            String fileName = "club/" + request.postId() + "." + request.file().getOriginalFilename().split("\\.")[1];

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(request.file().getContentType());
            metadata.setContentLength(request.file().getSize());

            amazonS3Client.putObject(bucket, fileName, request.file().getInputStream(), metadata);

            return amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new CustomException(FileError.FILE_CREATE_FAIL);
        }
    }
}
