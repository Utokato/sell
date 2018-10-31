package com.ml.sell.repository;

import com.ml.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234568");
        orderMaster.setBuyerName("hahdh");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("shabileba");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(9));

        OrderMaster result = repository.save(orderMaster);

    }

    @Test
    public void findByBuyerOpenid() {

        PageRequest request = PageRequest.of(0,3);

        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid(OPENID, request);

        orderMasters.stream().map(o -> o.getBuyerName()).collect(Collectors.toList()).forEach(s -> System.out.println(s));

    }
}