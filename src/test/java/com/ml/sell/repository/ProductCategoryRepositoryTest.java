package com.ml.sell.repository;

import com.ml.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void testFind() {
        List<ProductCategory> productCategories = repository.findAll();
        productCategories.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    public void testSave(){
        ProductCategory productCategory = repository.getOne(2);
        productCategory.setCategoryType(10);
        repository.save(productCategory);
    }

    @Test
    public void testFindByCategory(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> category = repository.findByCategoryTypeIn(list);
        category.forEach(c-> System.out.println(c.toString()));
    }

}