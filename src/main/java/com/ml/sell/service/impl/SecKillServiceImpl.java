package com.ml.sell.service.impl;

import com.ml.sell.exception.SellException;
import com.ml.sell.service.RedisLock;
import com.ml.sell.service.SecKillService;
import com.ml.sell.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2018/11/10
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    /**
     * 超时时间为10s
     */
    private static final int TIMEOUT = 10 * 1000;

    @Autowired
    private RedisLock redisLock;

    /**
     * 秒杀活动，限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "秒杀活动，小飞机限量，限量份: "
                + products.get(productId)
                + ", 还剩: " + stock.get(productId) + " 份,"
                + " 该商品成功下单用户数目: "
                + orders.size() + " 人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }


    /**
     * 在并发情况下，下面的代码执行会出现错误，可以使用synchronized关键字来加锁
     * 但是使用了synchronized加锁后，会产生执行效率低下的状况，并且控制的粒度比较粗大
     * <p>
     * 所以使用redis分布式锁来实现
     *
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {

        // 加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "抱歉呀，人太多了，刷新一下再试试~~");
        }

        // 1.查询该商品的库存，为0则活动结束
        Integer stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            // 2.下单(模拟不同的用户，openid不同)
            orders.put(KeyUtils.genUniqueKey(), productId);
            // 3.减少库存
            stockNum = stockNum - 1;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        // 解锁
        redisLock.unlock(productId, String.valueOf(time));
    }

}
