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
    public  String uploadImage(@RequestPart MultipartFile file) throws IllegalAccessException {
        return fileUploadService.uploadImage(file);
    }
}