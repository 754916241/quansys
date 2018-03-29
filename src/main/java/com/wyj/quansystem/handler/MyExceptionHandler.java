package com.wyj.quansystem.handler;

import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常处理类
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handle(Exception e){
        if(e instanceof ResultException){
            ResultException exception = (ResultException) e;
            return ResultUtils.error(exception.getCode(), exception.getMessage());
        }
        else{
            logger.error(e.getMessage());
            return ResultUtils.error(ResultEnum.Error.getCode(), e.getMessage());
        }

    }
}
