package com.ml.sell.controller;

import com.ml.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * skill is not technic
 *
 * This is just a short write for second kill.
 *
 * @author
 * @date 2018/11/10
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     *
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) {
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀
     * 没有抢到，返回错误信息
     * 抢到了，返回库存的余量
     * <p>
     * 使用Apache ab 测试
     * .\ab -n 10000 -c 100 http://127.0.0.1:8080/sell/skill/order/123456
     *
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String kill(@PathVariable String productId) {
        log.info("[ 秒杀活动 ] skill request, productId:" + productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }

}
