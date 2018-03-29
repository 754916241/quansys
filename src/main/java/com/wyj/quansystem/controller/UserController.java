package com.wyj.quansystem.controller;

import com.wyj.quansystem.bean.ResultBean;
import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.service.UserService;
import com.wyj.quansystem.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RestController
@RequestMapping("/userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value="login")
    public ResultBean login(@RequestBody UserBean user, HttpServletRequest request){
        ResultBean<UserBean> resultBean = ResultUtils.success(userService.login(user));
        request.getSession().setAttribute("id", userService.login(user).getId());
        request.getSession().setAttribute("userType", userService.login(user).getUserType());
        return resultBean;
    }

    @RequestMapping(value="isManager", method = RequestMethod.GET)
    public boolean isManager(HttpServletRequest request){
        String userType  = (String) request.getSession().getAttribute("userType");

        return userType != null && "admin".equals(userType);

    }

    @GetMapping(value="isLogin")
    public boolean isLogin(HttpServletRequest request){

        Integer id  = (Integer) request.getSession().getAttribute("id");
        logger.info(id+"");
        return id != null;
    }

    @RequestMapping(value="query", method = RequestMethod.POST)
    public Map<String, Object> query(@RequestBody UserBean user){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", userService.query(user));
        return resultMap;
    }


}
