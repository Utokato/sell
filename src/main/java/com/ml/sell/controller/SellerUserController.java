package com.ml.sell.controller;

import com.ml.sell.config.ProjectUrlConfig;
import com.ml.sell.constant.CookieConstant;
import com.ml.sell.constant.RedisConstant;
import com.ml.sell.dataobject.SellerInfo;
import com.ml.sell.enums.ResultEnum;
import com.ml.sell.service.SellerService;
import com.ml.sell.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户相关Controller
 *
 * @author
 * @date 2018/11/9
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig urlConfig;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        // 这里其实是做了妥协，本来应该使用微信授权来完成
        // 校验用户名和密码是否正确
        SellerInfo sellerInfoByUsername = sellerService.findSellerInfoByUsername(username);
        if(StringUtils.isEmpty(password) || !password.equals(sellerInfoByUsername.getPassword())){
            map.put("msg", ResultEnum.LOGOUT_FAIL_UAERNAME_OR_PASSWORD_ERROR.getMessage());
            map.put("url", "/sell/seller/order/list");
            log.error("[卖家登录信息] 登录失败：{} ", ResultEnum.LOGIN_FAIL.getMessage());
            return new ModelAndView("/login", map);
        }

        String openid = sellerInfoByUsername.getOpenid();

        // 1. openid 去和数据库中的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            log.error("[卖家登录信息] 登录失败：{} ", ResultEnum.LOGIN_FAIL.getMessage());
            return new ModelAndView("/login", map);
        }
        // 2. 设置token 去redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);
        // 3. 设置token 去cookie
        CookieUtils.set(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:" + urlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        // 1. 从cookie中查询token
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            // 2. 清除redis中的token
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            // 3. 清除cookie
            CookieUtils.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

}
