package com.wyj.quansystem.service.impl;

import com.wyj.quansystem.bean.CompanyBean;
import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.dao.CompanyDao;
import com.wyj.quansystem.dao.UserDao;
import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public UserBean login(UserBean userBean) {
        if(userDao.queryUser(userBean) != null){
            UserBean user = userDao.login(userBean);
            if(user != null){
                String userType = user.getUserType();
                if(userType.equals("company")){
                    CompanyBean company = companyDao.queryCompany(user.getId());
                    user.setCompany(company);
                }
                return user;
            }else{
                throw new ResultException(ResultEnum.PasswordError);
            }
        }else{
            throw new ResultException(ResultEnum.UserNotExist);
        }
    }

    @Override
    public UserBean query(UserBean userBean) {
        UserBean user = userDao.query(userBean);
        return user;
    }
}
