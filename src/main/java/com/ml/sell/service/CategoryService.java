package com.ml.sell.service;

import com.ml.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 商品类目服务
 *
 * @author
 */
public interface CategoryService {

    /**
     * 查询某一个商品类目
     *
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有的商品类目
     *
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 查找在某类型内的商品类目
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增商品类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
