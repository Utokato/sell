package com.ml.sell.service.impl;

import com.ml.sell.dataobject.ProductInfo;
import com.ml.sell.enums.ProductStatusEnum;
import com.ml.sell.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productServiceUpAll = productService.findUpAll();
        productServiceUpAll.forEach(System.out::println);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<ProductInfo> productInfos = productService.findAll(pageRequest);
        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("bingzi");
        productInfo.setProductPrice(new BigDecimal(5.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("ok");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(4);
        productService.save(productInfo);
    }

    @Test
    public void onSale(){
        ProductInfo result = productService.onSale("123456");
        Assert.assertEquals(ProductStatusEnum.UP,result.getProductStatusEnum());
    }

    @Test
    public void offSale(){
        ProductInfo result = productService.offSale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN,result.getProductStatusEnum());
    }
}