package com.example.ojbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ojbackend.common.StatusConstans;
import com.example.ojbackend.common.SystemConstants;
import com.example.ojbackend.common.interceptor.JwtInterceptor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ojbackend.entity.Problem;
import com.example.ojbackend.entity.User;
import com.example.ojbackend.entity.submit.SourceCode;
import com.example.ojbackend.entity.submit.SubmitResult;
import com.example.ojbackend.entity.Status;
import com.example.ojbackend.exception.ServiceException;
import com.example.ojbackend.judger.complier.CPlusCompiler;
import com.example.ojbackend.judger.complier.JavaCompiler;
import com.example.ojbackend.mapper.ProblemMapper;
import com.example.ojbackend.mapper.StatusMapper;
import com.example.ojbackend.mapper.UserMapper;
import com.example.ojbackend.service.ProblemService;
import com.example.ojbackend.service.StatusService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {
    @Resource
    private ProblemMapper problemMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProblemService problemService;

    @Resource
    private StatusMapper statusMapper;
    @Override
    public SubmitResult submitCode(SourceCode sourceCode) throws FileNotFoundException {
        System.out.println(sourceCode + "!!!");
        Problem problem;
        User user;
        Status status = new Status();
        try{
            problem = problemMapper.selectById(Integer.valueOf(sourceCode.getProblemId()));
            if(problem == null){
                throw new ServiceException(SystemConstants.CODE_601, "不存在此问题");
            }
        }catch (Exception e){
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        try{
            user = userMapper.selectById(Integer.valueOf(sourceCode.getUserId()));
            if(user == null){
                throw new ServiceException(SystemConstants.CODE_601, "不存在此用户");
            }
        }catch (Exception e){
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        status.setAuthor(user.getUsername());
        status.setProblem(problem.getTitle());
        status.setProblemid(problem.getId());
        String language = sourceCode.getLanguage();
        status.setLanguage(language);
        try (PrintWriter out = new PrintWriter("ojbackend/src/main/resources/static/judge_test/temp_execute_files/1.in")){
            out.println(problem.getInputData());
        } catch (Exception e) {
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        try (PrintWriter out = new PrintWriter("ojbackend/src/main/resources/static/judge_test/temp_execute_files/1.ans")){
            out.println(problem.getOutputData());
        } catch (Exception e) {
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
//        problem.getInputData()
        if(language.equals("c++")){
            try (PrintWriter out = new PrintWriter("ojbackend/src/main/resources/static/judge_test/temp_execute_files/main.cpp")) {
                out.println(sourceCode.getCode());
            }
            try{
                CPlusCompiler cPlusCompiler = new CPlusCompiler(problem.getInputData());
                cPlusCompiler.complier();
                if(StrUtil.isBlank(cPlusCompiler.getErrorMsg())) {
                    String fileName = "C:/Users/22845/Desktop/NOJ/ojbackend/src/main/resources/static/judge_test/temp_execute_files/result.txt";
                    // 读取文件内容到Stream流中，按行读取
                    Stream<String> lines = Files.lines(Paths.get(fileName));
                    // 随机行顺序进行数据处理
                    lines.forEach(ele -> {
                        System.out.println(ele);
                        if (ele.equals("ok")) {
                            problem.setAc(problem.getAc() + 1);
                            user.setAc(user.getAc() + 1);
                            status.setStatus(StatusConstans.CODE_200);
                        }
                        else {
                            status.setStatus(StatusConstans.CODE_202);
                        }
                    });
                }else {
                    status.setStatus(cPlusCompiler.getErrorMsg());
                }
            }catch (Exception e){
                throw new ServiceException(SystemConstants.CODE_500, "系统错误");
            }
        }
        else if(language.equals("Java")){
            try (PrintWriter out = new PrintWriter("ojbackend/src/main/resources/static/judge_test/temp_execute_files/main.java")) {
                out.println(sourceCode.getCode());
            }
            try{
                JavaCompiler javaCompiler = new JavaCompiler(problem.getInputData());
                javaCompiler.complier();
                if(StrUtil.isBlank(javaCompiler.getErrorMsg())) {
                    if (javaCompiler.getOutputData().equals(problem.getOutputData())) {
                        problem.setAc(problem.getAc() + 1);
                        status.setStatus(StatusConstans.CODE_200);
                    }else {
                        status.setStatus(StatusConstans.CODE_202);
                    }
                }else {
                    status.setStatus(StatusConstans.CODE_204);
                }
            }catch (Exception e){
                throw new ServiceException(SystemConstants.CODE_500, "系统错误");
            }
        }
        else throw new ServiceException(SystemConstants.CODE_601, "不存在此语言");
        problem.setTotal(problem.getTotal() + 1);
        user.setTot(user.getTot() + 1);
        problemMapper.updateById(problem);
        userMapper.updateById(user);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String dateNowStr = sdf.format(d);
        status.setDate(dateNowStr);
        save(status);
        SubmitResult submitResult = new SubmitResult();
        BeanUtil.copyProperties(status, submitResult, true);
        System.out.println(submitResult);
        return submitResult;
    }

    @Override
    public List<Status> index() {
        QueryWrapper<Status> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return statusMapper.selectList(wrapper);
    }
}
