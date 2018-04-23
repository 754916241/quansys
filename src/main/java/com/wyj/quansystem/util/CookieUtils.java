package com.wyj.quansystem.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {

    /**
     * 设置cookie返回至客户端
     * @param response 答复对象
     * @param name  cookie名称
     * @param value cookie内容
     * @param maxAge   持续时间
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 从cookie中获取相应数据
     * @param request
     * @param name
     */
    public static Cookie getCookie(HttpServletRequest request, String name){
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name))
            return cookieMap.get(name);
        return null;
    }

    /**
     * 直接获取cookie值
     * @param request
     * @param name
     */
    public static String getCookieValue(HttpServletRequest request, String name){
        Cookie cookie = getCookie(request, name);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 将cookie封装为map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
