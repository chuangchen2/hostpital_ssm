package com.hospital_ssm.controller;

import com.hospital_ssm.service.UploadService;
import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping("/image")
    public R updateImage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileName = uploadService.uploadImage(file);
        R r = new R();
        if (fileName == null) {
            r.setCode(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()));
            r.setMessage("图片上传失败！");
        } else {
            r.setCode(Integer.valueOf(HttpCodeUtil.OK.toString()));
            HashMap<String, String> data = new HashMap<>();
            data.put("picpath", fileName);
            r.setData(data);
        }
        return r;
    }
}
