package com.ml.sell.service;

import com.ml.sell.dto.OrderDTO;

/**
 * 消息服务
 *
 * @author
 * @date 2018/11/10
 */
public interface MessageService {


    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
