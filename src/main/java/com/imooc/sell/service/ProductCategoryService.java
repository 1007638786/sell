package com.imooc.sell.service;

import com.imooc.sell.dateobject.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {

    ProductCategory  findOne(Integer CategoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory  save(ProductCategory productCategory);




}
