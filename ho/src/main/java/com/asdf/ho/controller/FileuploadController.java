package com.asdf.ho.controller;

import com.asdf.ho.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileuploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart MultipartFile file) throws IllegalAccessException {
        if(file.isEmpty()) {
            throw new IllegalArgumentException("이미지 파일은 필수입니다.");
        }
        return fileUploadService.uploadImage(file);
    }
}