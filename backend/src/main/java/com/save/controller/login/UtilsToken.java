package com.save.controller.login;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.save.entity.Volunteer;
import com.save.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class UtilsToken {
    public static UtilsToken utilsToken;

    //保证单例的注解
    @PostConstruct
    public void init() {
        utilsToken = this;
    }

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    VolunteerService volunteerService;

    public  static String getToken(Integer userId){
        String uuid = UUID.randomUUID().toString();
        utilsToken.redisTemplate.opsForValue().set(uuid, userId, 30, TimeUnit.MINUTES);
        return uuid;
    }

    public static  Integer  tokenToId(String token){
        Object o = utilsToken.redisTemplate.opsForValue().get(token);
        return (Integer) o;
    }

    public static boolean checkToken(String token){
        Object id = utilsToken.redisTemplate.opsForValue().get(token);
        if(id!=null){
            //更新token
            utilsToken.redisTemplate.opsForValue().set(token, (Integer) id, 30, TimeUnit.MINUTES);
            return true;
        }else {
            return false;
        }
    }
}
