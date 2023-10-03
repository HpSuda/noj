package com.example.ojbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Comparable<User>{
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @JsonIgnore
    @TableField(value = "password")
    private String password;
    @TableField(value = "email")
    private String email;
    @TableField(value = "avatar_url")
    private String avatarUrl;
    @TableField(value = "github")
    private String github;
    @TableField(value = "signature")
    private String signature;
    @TableField(value = "accepted_num")
    private Integer ac;
    @TableField(value = "submit_num")
    private Integer tot;
    @Override
    public int compareTo(User user){
        if(this.ac.equals(user.ac))return this.tot - user.tot;
        return user.ac - this.ac;
    }
}
