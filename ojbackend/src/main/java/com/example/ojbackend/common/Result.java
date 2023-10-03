package com.example.ojbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String status_code;
    private String status_msg;
    private Object data;
    public static Result success(){
        return new Result(SystemConstants.CODE_200, "", null);
    }
    public static Result success(Object data){
        return new Result(SystemConstants.CODE_200, "", data);
    }
    public static Result error(String code, String msg){
        return new Result(code, msg, null);
    }
    public static Result error(){
        return new Result(SystemConstants.CODE_500, "系统错误", null);
    }
}
