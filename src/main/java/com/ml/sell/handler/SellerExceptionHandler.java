package com.ml.sell.handler;

import com.ml.sell.config.ProjectUrlConfig;
import com.ml.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @date 2018/11/9
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig urlConfig;

    /**
     * 拦截登录异常
     *
     * 这里其实就是捕获了用户未登录的异常，然后放回登录页面
     *
     * 但是，这里使用微信的扫码登录，所以无法实现
     *
     * 这个问题可以自己实现一个小的登录post表单来解决
     *
     * @return ModelAndView
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return new ModelAndView("redirect:".concat(urlConfig.getSell()).concat("/sell/seller/login"));
    }
}
