package com.example.ojbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ojbackend.common.Result;
import com.example.ojbackend.controller.dto.ProblemDto;
import com.example.ojbackend.entity.Problem;
import com.example.ojbackend.service.ProblemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Resource
    private ProblemService problemService;
    @GetMapping
//    @ResponseBody
    public List<Problem> index(){return problemService.list();}
    @GetMapping("/{id}")
    public Result findProblem(@PathVariable Integer id){
        ProblemDto problemDto = problemService.findProblem(id);
        return Result.success(problemDto);
    }
    @GetMapping("/page")
    public IPage<Problem> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(required = false) String problemName){
        IPage<Problem>page = new Page<>(pageNum, pageSize);
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        //queryWrapper.like("username",problemName);
        return problemService.page(page,queryWrapper);
    }
    @GetMapping("/{id}/md")
    public Result getProblemMd(@PathVariable Integer id) {
        return Result.success(problemService.getProblemMd(id));
    }
    @PostMapping("/createProblem")
    public Result createProblem(@RequestBody ProblemDto problemDto) {
        System.out.println("create");
        return Result.success(problemService.createProblem(problemDto));
    }
}