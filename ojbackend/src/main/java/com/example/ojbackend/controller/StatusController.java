package com.example.ojbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ojbackend.common.Result;
import com.example.ojbackend.controller.dto.ProblemDto;
import com.example.ojbackend.entity.Problem;
import com.example.ojbackend.entity.Status;
import com.example.ojbackend.entity.submit.SourceCode;
import com.example.ojbackend.service.StatusService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Resource
    private StatusService statusService;
    @GetMapping
    public List<Status> index(){return statusService.index();}
    @PostMapping("/submit")
    public Result submitCode(@RequestBody SourceCode sourceCode) throws FileNotFoundException {
        System.out.println("request submit");
        return Result.success(statusService.submitCode(sourceCode));
    }
    @GetMapping("/page")
    public IPage<Status> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(required = false) String statusName){
        IPage<Status>page = new Page<>(pageNum, pageSize);
        QueryWrapper<Status> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        return statusService.page(page,queryWrapper);
    }
}
