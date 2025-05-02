package com.aiit.common;

/**
 * @author hmxia
 * @date 2024/12/10 9:30
 * 异步响应规范格式类
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    //返回数据，
    public static<T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if(data != null){
            result.setData(data);
        }
        return result;
    }

    //返回数据
    public static<T> Result<T> build(T data,Integer code,String message){
        Result<T> result = build(data);
        result.setCode(code);
        result.setMessage(message);
        return  result;
    }


    //返回数据：从一定范围内选择状态码返回
    public static<T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return  result;
    }


    //操作成功的方法
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return  build(data,ResultCodeEnum.SUCCESS);
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data=data;
    }
}
