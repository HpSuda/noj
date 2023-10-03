package com.example.ojbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_status")
public class Status {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "submit_time")
    private String date;
    @TableField(value = "status")
    private String status;
    @TableField(value = "submit_username")
    private String author;
    @TableField(value = "submit_problemname")
    private String problem;
    @TableField(value = "submit_language")
    private String language;
    @TableField(value = "submit_problemid")
    private Integer problemid;
}
