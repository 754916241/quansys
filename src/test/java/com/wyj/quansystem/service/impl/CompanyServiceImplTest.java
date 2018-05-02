package com.wyj.quansystem.service.impl;

import com.wyj.quansystem.QuansystemApplicationTests;
import com.wyj.quansystem.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author wyj
 * @date 2018/4/21 10:16
 */
@Slf4j
public class CompanyServiceImplTest extends QuansystemApplicationTests{

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void acceptImage() {
    }

    @Test
    public void getInterview() {
    }

    @Test
    public void setValue(){
        UserBean bean = new UserBean();
        bean.setId(123);
        bean.setUserType("管理员");
        redisTemplate.opsForValue().set("qwer",bean);
    }

    @Test
    public void getValue(){
        UserBean bean = (UserBean) redisTemplate.opsForValue().get("qwer");
        log.info("usertype is {}", bean.getUserType());
        log.info("userid is {}", bean.getId());
    }
}