package com.wyj.quansystem.controller;

import com.wyj.quansystem.annotation.TokenValue;
import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.service.UserService;
import com.wyj.quansystem.util.Constant;
import com.wyj.quansystem.util.CookieUtils;
import com.wyj.quansystem.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.wyj.quansystem.util.Constant.expire;

@RestController
@RequestMapping("/userController")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String, UserBean> redisTemplate;

    @PostMapping(value="login")
    public ResultBean login(@RequestBody UserBean user, HttpServletResponse response){
        UserBean result = userService.login(user);
        ResultBean<UserBean> resultBean = ResultUtils.success(result);
        //request.getSession().setAttribute("id", userService.login(user).getId());
        //request.getSession().setAttribute("userType", userService.login(user).getUserType());

        String token = UUID.randomUUID().toString();
        // 设置token与userType至redis
        redisTemplate.opsForValue().set(token, new UserBean(result.getId(), result.getUserType()), expire, TimeUnit.SECONDS);
        //设置token到cookie
        CookieUtils.setCookie(response, Constant.token, token, expire);
        return resultBean;
    }

    @GetMapping(value="isManager")
    public boolean isManager(@TokenValue String token){
        // redis中存在token并且userType为admin
        if(isLogin(token)){
           String userType = redisTemplate.opsForValue().get(token).getUserType();
           return "admin".equals(userType);
        }
        return false;
    }

    @GetMapping(value="isLogin")
    public boolean isLogin(@TokenValue String token){
        if(token != null){
            Integer id = redisTemplate.opsForValue().get(token).getId();
            return id != null;
        }
        return false;
    }

    @GetMapping(value="logout")
    public ResultBean logout(@TokenValue String token, HttpServletResponse response){  // @CookieValue("token") String token
        //将token清除出redis以及cookie
        if(token != null){
            redisTemplate.opsForValue().getOperations().delete(token);
            CookieUtils.setCookie(response, Constant.token, null, 0);
        }
        return ResultUtils.success();
    }

}
