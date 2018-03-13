package com.wyj.quansystem.controller;

import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody UserBean user, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        if(userService.login(user) != null){
            request.getSession().setAttribute("id", user.getId());
            System.out.println(request.getSession().getId());
            resultMap.put("status", 0);
            resultMap.put("userType", userService.login(user).getUserType());
        }else{
            resultMap.put("status", 1);
        }
        return resultMap;
    }

    @RequestMapping(value="isLogin1", method = RequestMethod.GET)
    public Map<String, Object> isLogin1(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        Integer id  = (Integer) request.getSession().getAttribute("id");
        System.out.println(request.getSession().getAttributeNames().nextElement());
        if(id != null){
            resultMap.put("status", 200);
        }else{
            resultMap.put("status", 400);
        }
        return resultMap;
    }

    @RequestMapping(value="isLogin", method = RequestMethod.GET)
    public boolean isLogin(HttpServletRequest request){

        Integer id  = (Integer) request.getSession().getAttribute("id");
        System.out.println(request.getSession().getAttributeNames().hasMoreElements());
        return id != null;
    }


}
