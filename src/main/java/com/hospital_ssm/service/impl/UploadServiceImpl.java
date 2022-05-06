package com.hospital_ssm.service.impl;

import com.hospital_ssm.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${upload.file.path}")
    String filePath;

    @Override
    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffixName;
        File savedFile = new File(filePath + "images/" + fileName);
        try {
            file.transferTo(savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "/images/" + fileName;
    }
}
