package com.ml.sell.dto;

import lombok.Data;

/**
 * 购物车DTO对象
 *
 * Data Transfer Object数据传输对象
 *
 * @author
 */
@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
