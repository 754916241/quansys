package com.wyj.quansystem.enums;

public enum ResultEnum {

    Error(-1, "未知错误"),
    Success(200, "请求成功"),
    UserNotExist(100, "用户不存在"),
    PasswordError(101, "账号或密码错误"),
    FailTwo(102, "数据库中不存在该信息"),
    DataBaseError(103, "数据库错误"),
    LoginTimeOut(104, "已超时，请重新登录"),
    RedisError(105, "缓存数据有误，请稍后重试"),
    ;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
