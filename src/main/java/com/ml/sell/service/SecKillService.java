package com.ml.sell.service;

/**
 * @author
 * @date 2018/11/10
 */
public interface SecKillService {

    /**
     * 查询秒杀商品的余量
     *
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 秒杀商品下单
     *
     * @param productId
     */
    void orderProductMockDiffUser(String productId);

}
