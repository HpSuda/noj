package com.example.ojbackend.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String token;
    private String avatarUrl;
    private String github;
    private String signature;
    private String id;
}
