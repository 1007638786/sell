package com.imooc.sell.service.impl;

import com.imooc.sell.dateobject.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
@Autowired
    private ProductCategoryServiceImpl  categoryService;

    @Test
    public void findOne() {
        ProductCategory one = categoryService.findOne(1);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());

    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> intlist = Arrays.asList(1, 2, 3);
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(intlist);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(3);
        productCategory.setCategoryName("大众最爱");
        ProductCategory category = categoryService.save(productCategory);
       Assert.assertNotNull(category);
    }
}