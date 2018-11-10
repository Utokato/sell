package com.ml.sell.dataobject.dao;

import com.ml.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Dao 层，封装了mapper文件
 * 然后在service的impl中直接引入dao就可以使用
 * 多一层封装可以更加灵活的控制
 *
 * @author
 * @date 2018/11/10
 */
@Repository
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int insert(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }

}
