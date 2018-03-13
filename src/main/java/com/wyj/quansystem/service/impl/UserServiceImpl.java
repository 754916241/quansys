package com.wyj.quansystem.service.impl;

import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.dao.UserDao;
import com.wyj.quansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBean login(UserBean userBean) {
        if(userDao.queryUser(userBean) != null){
            return userDao.queryUser(userBean);
        }
        return null;
    }
}
