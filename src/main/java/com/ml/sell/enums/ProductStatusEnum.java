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

    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;


}
