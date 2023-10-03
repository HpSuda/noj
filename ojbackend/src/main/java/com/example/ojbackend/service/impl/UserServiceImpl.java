package com.example.ojbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ojbackend.common.SystemConstants;
import com.example.ojbackend.common.interceptor.JwtInterceptor;
import com.example.ojbackend.controller.dto.UserDto;
import com.example.ojbackend.entity.User;
import com.example.ojbackend.exception.ServiceException;
import com.example.ojbackend.mapper.UserMapper;
import com.example.ojbackend.service.FilesService;
import com.example.ojbackend.service.UserService;
import com.example.ojbackend.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Log LOG = Log.get();
    @Resource
    private FilesService filesService;

    @Resource
    private UserMapper userMapper;
    @Override
    public UserDto login(UserDto userDto) {
        User one;
        try{
            one = getUserInfo(userDto, 1);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        if(one != null){
            BeanUtil.copyProperties(one, userDto, true);
            String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
            userDto.setToken(token);
            userDto.setId(one.getId().toString());
            return userDto;
        }else {
            throw new ServiceException(SystemConstants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDto userDto) {
        User one;
        try{
            one =getUserInfo(userDto, 0);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
        if(one == null){
            one =new User();
            BeanUtil.copyProperties(userDto, one, true);
            save(one);
            return one;
        }else {
            throw new ServiceException(SystemConstants.CODE_601, "账号已存在");
        }
    }

    @Override
    public UserDto updateUserInfo(UserDto userDto) throws IOException {
        User one;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", Integer.valueOf(userDto.getId()));
        one = getOne(queryWrapper);
        if(one == null){
            throw new ServiceException(SystemConstants.CODE_600, "密码错误");
        }
        User updateCase = new User();
        BeanUtil.copyProperties(userDto, updateCase, true);
        updateCase.setId(one.getId());
        updateById(updateCase);
        String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
        userDto.setToken(token);
        return userDto;
    }
    @Override
    public IPage<User> rank(Integer pageNum, Integer pageSize) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("ac");
            queryWrapper.orderByAsc("tot");
            IPage<User> page = new Page<User>(pageNum, pageSize);
            return page(page, queryWrapper);
        }catch (Exception e){
            throw new ServiceException(SystemConstants.CODE_500, "系统错误");
        }
    }

    private User getUserInfo(UserDto userDto, Integer queryCode) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        if(queryCode == 1)queryWrapper.eq("password", userDto.getPassword());
        return getOne(queryWrapper);
    }
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}