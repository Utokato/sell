package com.ml.sell.enums;

import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    /**
     * 订单状态：新订单
     */
    NEW(0, "新订单"),
    /**
     * 订单状态：已完结订单
     */
    FINISHED(1, "完结"),
    /**
     * 订单状态：已取消订单
     */
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
