package com.ml.sell.repository;

import com.ml.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品信息DAO
 *
 * Data Access Object 数据访问对象
 *
 * @author
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据商品状态查询，返回商品信息列表
     *
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
