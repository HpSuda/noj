package com.example.ojbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_problem")
public class Problem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "problem_title")
    private String title;
    @TableField(value = "submission_times")
    private Integer total;
    @TableField(value = "accepted_times")
    private Integer ac;
    @TableField(value = "description")
    private String description;
    @TableField(value = "input_description")
    private String inputDescription;
    @TableField(value = "output_description")
    private String outputDescription;
    @TableField(value = "input_data")
    private String inputData;
    @TableField(value = "output_data")
    private String outputData;
    @TableField(value = "data_domain")
    private String dataDomain;
    @TableField(value = "level")
    private String level;
}
