package com.gec.util;

public class Result<T> {

    private String code; // 状态码
    private String msg; //提示信息
    private T date; // 返回的数据

    public static Result success(){
        Result result = new Result();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result success(T date){
        Result<T> result = new Result<>(date);
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    public static Result error(String code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


    public Result() {
    }

    public Result(T date) {
        this.date = date;
    }

    public Result(String code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }


}
