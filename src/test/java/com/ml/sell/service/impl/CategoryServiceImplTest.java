package com.ml.sell.service.impl;

import com.ml.sell.dataobject.ProductCategory;
import com.ml.sell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void findOne() {
        ProductCategory productCategory = service.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categories = service.findAll();
        Assert.assertNotNull(categories);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryListResult = service.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertNotNull(productCategoryListResult);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(4);
        ProductCategory result = service.save(productCategory);
        Assert.assertNotNull(result);
    }
}