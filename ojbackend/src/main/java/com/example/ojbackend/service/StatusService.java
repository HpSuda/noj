package com.example.ojbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ojbackend.entity.Status;
import com.example.ojbackend.entity.submit.SourceCode;
import com.example.ojbackend.entity.submit.SubmitResult;

import java.io.FileNotFoundException;
import java.util.List;

public interface StatusService extends IService<Status> {
    SubmitResult submitCode(SourceCode sourceCode) throws FileNotFoundException;

    List<Status> index();
}
