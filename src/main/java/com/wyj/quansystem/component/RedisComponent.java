package com.wyj.quansystem.component;

import com.mysql.jdbc.StringUtils;
import com.wyj.quansystem.bean.UserBean;
import com.wyj.quansystem.enums.ResultEnum;
import com.wyj.quansystem.exception.ResultException;
import com.wyj.quansystem.util.Constant;
import com.wyj.quansystem.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Component
@Slf4j
public class RedisComponent {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, UserBean> redisTemplate;

    /**
     *
     * @param key 要锁的东西，例如抢购时产品的id等，总之，就是代表这个锁是谁加的
     * @param value 标识，一般用当前时间+锁的过期时间
     * @return true:成功加锁；false:失败
     */
    public boolean lock(String key, String value){
        //若key对应的value不存在，则设置value并返回true，若存在，则返回false
       if(stringRedisTemplate.opsForValue().setIfAbsent(key, value)){
           return true;
       }

       String currentValue = stringRedisTemplate.opsForValue().get(key);
       //判断当前锁是否过期
        if(!StringUtils.isNullOrEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()){
            /*
             * 由于redis为单进程单线程模式，采用队列模式将并发访问变成串行访问，且多客户端对Redis的连接并不存在竞争关系,
             * 所以这段代码是为了防止多线程同时得到锁情况的发生。
             * 假设有两个线程a,b同时访问，第一次getAndSet(key, value)得到oldValue的值应该是currentValue的值，所以如果
             * oldValue.equals(currentValue)，则代表第一个线程拿到了锁，加锁成功，返回true。第二个线程getAndSet(key, value)
             * 得到oldValue的值应该是第一个线程留下的value，他和currentValue并不相等，这个时候应该返回false，代表已被其他线程
             * 锁住，加锁失败。
             * 想的真j8对！
             */
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isNullOrEmpty(currentValue) && oldValue.equals(currentValue)){
                return true;
            }
        }

       return false;
    }

    public void unLock(String key, String value){
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        try {
            if(!StringUtils.isNullOrEmpty(currentValue) && currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("redis分布式锁解锁异常， {}", e);
        }
    }

    public Integer getIdFromRequest(HttpServletRequest request){
        String token = CookieUtils.getCookieValue(request, Constant.token);
        return getIdFromToken(token);
    }

    public Integer getIdFromToken(String token){
        UserBean userBean = redisTemplate.opsForValue().get(token);
        if(userBean == null){
            throw new ResultException(ResultEnum.RedisError);
        }
        return userBean.getId();
    }
}
