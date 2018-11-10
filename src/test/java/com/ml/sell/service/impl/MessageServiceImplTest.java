package com.ml.sell.service.impl;

import com.ml.sell.dto.OrderDTO;
import com.ml.sell.service.MessageService;
import com.ml.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1541507636408598104");
        // 使用如下方法，可以向微信用户推送消息，但是这里仍然 mock
        // messageService.orderStatus(orderDTO);
    }
}