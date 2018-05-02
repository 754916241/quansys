package com.wyj.quansystem.util;

import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.enums.ResultEnum;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;

public class ResultUtils {

    public static <T> ResultBean<T> success(T data){
        ResultBean<T> bean = new ResultBean<>();
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

    public static ResultBean error(){
        return error(ResultEnum.Error.getCode(), ResultEnum.Error.getMsg());
    }


}
