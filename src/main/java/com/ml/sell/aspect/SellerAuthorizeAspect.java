package com.ml.sell.aspect;

import com.ml.sell.constant.CookieConstant;
import com.ml.sell.constant.RedisConstant;
import com.ml.sell.exception.SellerAuthorizeException;
import com.ml.sell.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2018/11/9
 */
@Slf4j
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.ml.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.ml.sell.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 查询cookie
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.info("[登录校验] Cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        // 去redis中获取token
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.info("[登录校验] Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }

}
