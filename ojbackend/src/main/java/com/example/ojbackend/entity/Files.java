package com.example.ojbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_file")
public class Files {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "type")
    private String type;
    @TableField(value = "size")
    private Long size;
    @TableField(value = "url")
    private String url;
    @TableField(value = "md5")
    private String md5;
    @TableField(value = "is_delete")
    private Boolean isDelete;
    @TableField(value = "enable")
    private Boolean enable;
}
