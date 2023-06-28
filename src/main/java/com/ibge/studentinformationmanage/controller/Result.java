package com.ibge.studentinformationmanage.controller;

import lombok.Data;

@Data
public class Result<T> {
    private Object data;
    private Integer code;
    private String msg;
    public static <T> Result<T> success(T object,Integer code) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = code;
        return r;
    }

    public static <T> Result<T> error(String msg,Integer code) {
        Result r = new Result<>();
        r.msg = msg;
        r.code = code;
        return r;
    }
}
