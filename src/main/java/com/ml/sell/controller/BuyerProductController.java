package com.ml.sell.controller;

import com.ml.sell.vo.ProductInfoVO;
import com.ml.sell.vo.ProductVO;
import com.ml.sell.vo.ResultVO;
import com.ml.sell.dataobject.ProductCategory;
import com.ml.sell.dataobject.ProductInfo;
import com.ml.sell.service.CategoryService;
import com.ml.sell.service.ProductService;
import com.ml.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家-商品 控制层
 *
 * @author
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有上架的商品列表
     *
     * @return
     * @Cacheable 注解参数说明：
     *      cacheNames 缓存的名称
     *      key 缓存次级的名称
     *      unless 对返回的结果进行判断，满足条件才进行缓存
     *      condition 对某一条件进行判断，满足条件才进行缓存
     * <p>
     * 同时，需要注意的是：进行缓存的对象，必须实现序列化接口。可以使用IDEA的插件来生成序列化id
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "1301e53", unless = "#result.getCode() != 0")
    public ResultVO list() {
        // 1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2. 查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }


}
