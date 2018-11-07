package com.ml.sell.service;

import com.ml.sell.dataobject.ProductInfo;
import com.ml.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务
 *
 * @author
 */
public interface ProductService {

    /**
     * 查询某一个商品信息
     *
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有上架的商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询商品列表
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 新增一条商品信息
     *
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);


    /**
     * 给某一商品加库存
     *
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 给某一商品减库存
     *
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     *
     * @param productId
     * @return ProductInfo
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     *
     * @param productId
     * @return ProductInfo
     */
    ProductInfo offSale(String productId);

}
