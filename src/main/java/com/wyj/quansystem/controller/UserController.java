package com.wyj.quansystem.controller;

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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping(value="login")
    public ResultBean login(@RequestBody UserBean user, HttpServletResponse response){
        UserBean result = userService.login(user);
        ResultBean<UserBean> resultBean = ResultUtils.success(result);
        //request.getSession().setAttribute("id", userService.login(user).getId());
        //request.getSession().setAttribute("userType", userService.login(user).getUserType());

        String token = UUID.randomUUID().toString();
        // 设置token与userType至redis
        redisTemplate.opsForValue().set(token, result.getId()+"", expire, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("userType", result.getUserType(), expire, TimeUnit.SECONDS);
        //设置token到cookie
        CookieUtils.setCookie(response, Constant.token, token, expire);
        return resultBean;
    }

    @RequestMapping(value="isManager", method = RequestMethod.GET)
    public boolean isManager(HttpServletRequest request){
        // redis中存在token并且userType为admin
        return isLogin(request) && ("admin").equals(redisTemplate.opsForValue().get("userType"));
    }

    @GetMapping(value="isLogin")
    public boolean isLogin(HttpServletRequest request){
        Cookie cookie = CookieUtils.getCookie(request, Constant.token);
        // Integer id  = (Integer) request.getSession().getAttribute("id");
        if(cookie != null){
            String id = redisTemplate.opsForValue().get(cookie.getValue());
            return id != null;
        }
        return false;
    }

    /*@RequestMapping(value="query", method = RequestMethod.POST)
    public Map<String, Object> query(@RequestBody UserBean user){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", userService.query(user));
        return resultMap;
    }*/

    @GetMapping(value="logout")
    public ResultBean logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = CookieUtils.getCookie(request, Constant.token);
        //将token清除出redis以及cookie
        if(cookie != null){
            redisTemplate.opsForValue().getOperations().delete(cookie.getValue());
            CookieUtils.setCookie(response, Constant.token, null, 0);
        }
        return ResultUtils.success();
    }

}
