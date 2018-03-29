package com.wyj.quansystem.exception;

import com.wyj.quansystem.enums.ResultEnum;

/**
 * 自定义异常类
 */
public class ResultException extends RuntimeException{

    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
