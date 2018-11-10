package com.ml.sell.dataobject.mapper;

import com.ml.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * the accidence for mybatis
 *
 * @author
 * @date 2018/11/10
 */
@Component
public interface ProductCategoryMapper {

    /**
     * 使用mybatis注解的方式向数据库中插入一条数据
     *
     * @param map
     * @return
     */
    @Insert("INSERT INTO product_category(category_name,category_type) VALUES (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    /**
     * 使用mybatis注解的方式向数据库中插入一条数据
     *
     * @param productCategory
     * @return
     */
    @Insert("INSERT INTO product_category(category_name,category_type) VALUES (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    /**
     * 使用mybatis注解的方式 查询一条记录
     *
     * @param categoryType
     * @return
     */
    @Select("SELECT * FROM product_category WHERE category_type = #{categoryType} ")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 使用mybatis注解的方式 查询多条记录
     *
     * @param categoryName
     * @return
     */
    @Select("SELECT * FROM product_category WHERE category_name = #{categoryName} ")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    /**
     * 使用mybatis注解的方式 更新记录 使用某一字段
     * <p>
     * 当传入多个参数是，需要使用@Param注解来进行区分
     *
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("UPDATE product_category SET category_name = #{categoryName} WHERE category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    /**
     * 使用mybatis注解的方式 更新记录 使用对象
     *
     * @param productCategory
     * @return
     */
    @Update("UPDATE product_category SET category_name = #{categoryName} WHERE category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    /**
     * 使用mybatis注解的方式 删除记录
     *
     * @param categoryType
     * @return
     */
    @Delete("DELETE FROM product_category WHERE category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    /**
     * 使用xml文件 查询一条记录
     *
     * @param categoryType
     * @return
     */
    ProductCategory selectByCategoryType(Integer categoryType);

}
