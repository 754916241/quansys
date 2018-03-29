package com.wyj.quansystem.enums;

public enum ResultEnum {

    Error(-1, "未知错误"),
    Success(200, "请求成功"),
    FailOne(1, "前台请求数据有误"),
    FailTwo(2, "数据库中不存在该信息"),
    FailThree(3, "数据库错误"),
    LoginFail1(100, "用户不存在"),
    LoginFail2(101, "账号或密码错误"),
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
