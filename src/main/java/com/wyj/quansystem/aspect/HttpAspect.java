package com.wyj.quansystem.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * spring中面向切面的写法
 */
@Component
@Aspect
public class HttpAspect {

    @Pointcut("execution(public * com.wyj.quansystem.controller.UserController.clone(..))")
    public void log(){

    }

    @Before("log()")
    public void before(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
    }

    @After("log()")
    public void after(){

    }

    @AfterReturning(pointcut = "log()", returning = "object")
    public void returning(Object object){

    }
}
