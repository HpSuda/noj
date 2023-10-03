package com.example.ojbackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ojbackend.controller.dto.UserDto;
import com.example.ojbackend.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService extends IService<User> {
    UserDto login(UserDto userdto);

    User register(UserDto userDto);

    UserDto updateUserInfo(UserDto userDto) throws IOException;

    IPage<User> rank(Integer pageNum, Integer pageSize);

    User getUserById(Integer id);

}
