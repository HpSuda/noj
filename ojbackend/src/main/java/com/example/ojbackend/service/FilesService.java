package com.example.ojbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ojbackend.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService extends IService<Files> {
    String upload(MultipartFile file) throws IOException;
}
