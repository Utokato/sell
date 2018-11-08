package com.ml.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态枚举类
 *
 * @author
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum {

    /**
     * 商品状态：上架中
     */
    UP(0, "在架"),
    /**
     * 商品状态：已下架
     */
    DOWN(1, "下架");

    private Integer code;

    private String message;


}
