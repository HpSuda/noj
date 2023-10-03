package com.example.ojbackend.controller;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.example.ojbackend.common.SystemConstants;
import com.example.ojbackend.common.Result;
import com.example.ojbackend.controller.dto.UserDto;
import com.example.ojbackend.entity.User;
import com.example.ojbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

import static com.example.ojbackend.common.SystemConstants.CODE_401;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping
    public List<User> index(){
        return userService.list();
    }
    @GetMapping("/rank")
    public Result rank(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize){
        return Result.success(userService.rank(pageNum, pageSize));
    }
    @GetMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password){
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(SystemConstants.CODE_400, "参数错误");
        }
        UserDto dto = userService.login(userDto);
        return Result.success(dto);
    }
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto){
        System.out.println(userDto);
        User user = userService.register(userDto);
        return Result.success(user);
    }
    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(required = false) String username){
        IPage<User>page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        return userService.page(page,queryWrapper);
    }
    @GetMapping("/curUser")
    public Result curUser(@RequestParam String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        System.out.println(userId);
        return Result.success(userService.getUserById(Integer.valueOf(userId)));
    }
    @GetMapping("/update")
    public Result update(@RequestParam String token,
                         @RequestParam String Username,
                         @RequestParam String oldPassword,
                         @RequestParam String newPassword,
                         @RequestParam String email) {
        System.out.println(token);
        System.out.println(Username);
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(email);
        if (token.equals("")) {
            return Result.error(CODE_401, "没有token");
        }
        String userId = JWT.decode(token).getAudience().get(0);
        if (userId.equals("")) {
            return Result.error(CODE_401, "用户错误");
        }
        Integer uid = Integer.valueOf(userId);
        User cur = userService.getUserById(uid);
        if (cur.getPassword().equals(oldPassword)) {
            UserDto userDto = new UserDto();
            userDto.setToken(token);
            userDto.setEmail(email);
            userDto.setUsername(Username);
            userDto.setPassword(newPassword);
            userDto.setId(userId);
            try {
                return Result.success(userService.updateUserInfo(userDto));
            }catch (IOException e) {
                return Result.error(CODE_401, "错误");
            }
        }
        return Result.success();
    }
}
