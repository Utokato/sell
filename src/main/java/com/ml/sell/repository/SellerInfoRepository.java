package com.ml.sell.repository;

import com.ml.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息dao
 * @author
 * @date 2018/11/9
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    /**
     * 根据openid查询卖家信息
     * @param openid
     * @return 卖家信息
     */
    SellerInfo findByOpenid(String openid);

    /**
     * 根据用户名查询卖家信息
     * @param username
     * @return 卖家信息
     */
    SellerInfo findByUsername(String username);
}
