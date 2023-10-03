package com.example.ojbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ojbackend.controller.ProblemController;
import com.example.ojbackend.controller.dto.ProblemDto;
import com.example.ojbackend.entity.Problem;

public interface ProblemService extends IService<Problem> {

    ProblemDto findProblem(Integer problemId);

    String getProblemMd(Integer problemId);

    Problem createProblem(ProblemDto problemDto);
}
