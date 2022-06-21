package com.machaojin.common;

import lombok.Data;

/**
 * @author 马超金
 * @version 1.0
 * @date 2022/6/14 10:52
 */

@Data
public class Result<T> {
    private T data;
    private String message;
    private int code;
    public static<T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        result.message = "success";
        return result;
    }
    public static<T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.code = 0;
        result.data = null;
        result.message = "error";
        return result;
    }
}
