package com.ml.sell.service;

import com.ml.sell.dataobject.SellerInfo;

/**
 * 卖家端接口
 *
 * @author
 * @date 2018/11/9
 */
public interface SellerService {

    /**
     * 根据openid查询卖家信息
     *
     * @param openid
     * @return 卖家信息
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    /**
     * 根据openid查询卖家信息
     * @param username
     * @return 卖家信息
     */
    SellerInfo findSellerInfoByUsername(String username);

}
