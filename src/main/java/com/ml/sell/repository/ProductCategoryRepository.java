package com.ml.sell.repository;

import com.ml.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目DAO
 *
 * Data Access Object 数据访问对象
 *
 * @author
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据类目类型列表查询类目列表
     * @param categoryTypeList
     * @return 商品类目列表
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
