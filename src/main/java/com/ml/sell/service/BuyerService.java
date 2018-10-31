package com.ml.sell.service;

import com.ml.sell.dto.OrderDTO;

/**
 * 买家服务
 *
 * @author
 */
public interface BuyerService {

    /**
     * 买家根据openid和orderId查询一个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 买家根据openid和orderId取消一个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);
}
