package com.ml.sell.enums;

import lombok.Getter;

/**
 * 支付状态枚举类
 *
 * @author
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    /**
     * 支付状态：待支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付状态：已支付成功
     */
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
