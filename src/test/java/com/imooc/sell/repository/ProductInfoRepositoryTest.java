package com.imooc.sell.repository;

import com.imooc.sell.dateobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository  infoRepository;

    @Test
    public void save(){
        ProductInfo info = new ProductInfo();
        info.setProductId(UUID.randomUUID().toString().substring(10).replace("-",""));
        info.setProductName("小米粥");
        info.setProductPrice(new BigDecimal(3.2));
        info.setProductStock(100);
        info.setProductDescription("香甜软糯");
        info.setProductIcon("tupiantupaindizhitupaindizhi");
        info.setProductStatus(0);
        info.setCategoryType(1);
        ProductInfo save = infoRepository.save(info);
        Assert.assertNotNull(save);


    }

    @Test
    public void findByProductStatus() throws  Exception{
        List<ProductInfo> byProductStatus = infoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,byProductStatus.size());
    }
}