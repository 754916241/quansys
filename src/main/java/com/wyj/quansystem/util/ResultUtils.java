package com.wyj.quansystem.util;

import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.enums.ResultEnum;

public class ResultUtils {

    public static <T> ResultBean<T> success(T data){
        ResultBean bean = new ResultBean();
        bean.setCode(ResultEnum.Success.getCode());
        bean.setMsg(ResultEnum.Success.getMsg());
        bean.setData(data);
        return bean;
    }

    public static ResultBean success(){
        return success(null);
    }

    public static ResultBean error(Integer code, String msg){
        ResultBean bean = new ResultBean();
        bean.setCode(code);
        bean.setMsg(msg);
        return bean;
    }


}
