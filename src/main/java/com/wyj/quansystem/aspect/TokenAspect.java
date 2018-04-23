package com.wyj.quansystem.aspect;

import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.util.Constant;
import com.wyj.quansystem.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyj
 * @date 2018/4/21 16:25
 */
@Component
@Aspect
@Slf4j
public class TokenAspect {

    @Pointcut("within(com.wyj.quansystem.controller.* && !com.wyj.quansystem.controller.UserController)")
    public void valid(){

    }

    @Before("valid()")
    public void hasToken(){
        log.info("controller before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = CookieUtils.getCookieValue(request, Constant.token);
        if(token == null){
            throw new ResultException(ResultEnum.LoginTimeOut);
        }
    }
}
