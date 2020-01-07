package com.imooc.sell.repository;

import com.imooc.sell.dateobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductCategoryRepositoryTest {

   @Autowired
   private ProductCategoryRepository repository;

   @Test
    public void ProductCategory(){
      ProductCategory productCategory= repository.findById(1).orElse(null);
      System.out.println(productCategory.toString());
        log.info(productCategory.toString());
   }
    @Test
    public void  SaveProductCategory(){
      ProductCategory  productCategory  = new ProductCategory();
//        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(2);
        ProductCategory save = repository.save(productCategory);
        Assert.assertNotNull(save);
    }
//   @Test
//    public void findByCategoryTypeInTest(){
//       List<Integer> list = Arrays.asList(2,3);
//       List<ProductCategory> proList = repository.findByCategoryTypeIn(list);
//       Assert.assertNotEquals(0,proList.size());
//
//   }
@Test
public void findByCategoryTypeInTest() {
    List<Integer> list = Arrays.asList(1,2);
    List<ProductCategory> result = repository.findByCategoryTypeIn(list);

}
}