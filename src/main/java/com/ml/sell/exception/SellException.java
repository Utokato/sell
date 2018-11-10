package com.ml.sell.exception;

import com.ml.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * 项目自定义异常类
 *
 * 继承了运行时异常类
 *
 * @author
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
