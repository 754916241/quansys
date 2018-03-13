package com.wyj.quansystem.service;

import com.wyj.quansystem.bean.UserBean;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    UserBean login(UserBean userBean);
}
