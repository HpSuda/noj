package com.example.ojbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ojbackend.common.SystemConstants;
import com.example.ojbackend.controller.dto.ProblemDto;
import com.example.ojbackend.entity.Problem;
import com.example.ojbackend.entity.User;
import com.example.ojbackend.exception.ServiceException;
import com.example.ojbackend.mapper.ProblemMapper;
import com.example.ojbackend.service.ProblemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
    private static final Log LOG = Log.get();
    @Resource
    private ProblemMapper problemMapper;
    @Override
    public ProblemDto findProblem(Integer problemId) {
        Problem problem;
        try {
            problem = problemMapper.selectById(problemId);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        if(problem == null)throw new ServiceException(SystemConstants.CODE_600, "找不到题目");
        ProblemDto problemDto = new ProblemDto();
        BeanUtil.copyProperties(problem, problemDto, true);
        return problemDto;
    }

    @Override
    public String getProblemMd(Integer problemId){
        Problem problem = problemMapper.selectById(problemId);
        System.out.println(problem.getDescription());
        return problem.getDescription();
    }

    @Override
    public Problem createProblem(ProblemDto problemDto) {
        Problem one = new Problem();
        BeanUtil.copyProperties(problemDto, one, true);
        if (one.getLevel().equals("0")) one.setLevel("easy");
        else if (one.getLevel().equals("1")) one.setLevel("medium");
        else if (one.getLevel().equals("2")) one.setLevel("hard");
        save(one);
        return one;
    }
}