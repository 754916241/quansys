package com.wyj.quansystem.dao;

import com.wyj.quansystem.bean.UserBean;

public interface UserDao {

    UserBean queryUser(UserBean user);

    UserBean query(UserBean user);

    UserBean login(UserBean userBean);
}
