package com.ml.sell.handler;

import com.ml.sell.config.ProjectUrlConfig;
import com.ml.sell.exception.ResponseException;
import com.ml.sell.exception.SellException;
import com.ml.sell.exception.SellerAuthorizeException;
import com.ml.sell.utils.ResultVOUtils;
import com.ml.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
     * <p>
     * 这里其实就是捕获了用户未登录的异常，然后放回登录页面
     * <p>
     * 但是，这里使用微信的扫码登录，所以无法实现
     * <p>
     * 这个问题可以自己实现一个小的登录post表单来解决
     *
     * @return ModelAndView
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException() {
        return new ModelAndView("redirect:".concat(urlConfig.getSell()).concat("/sell/seller/login"));
    }

    /**
     * 出现异常时，给前端返回自定义的错误码格式
     *
     * @param e
     * @return Json
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * 自定义返回的HTTP状态
     *
     * 具体操作：
     *  首先自定义一个异常，如：ResponseException
     *  在发生错误的位置，抛出该异常
     *  在这个handler里捕获该异常
     *  通过设置@ResponseStatus注解给前端返回不同的HTTP状态
     */
    @ExceptionHandler(value = ResponseException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseException() {

    }

}
