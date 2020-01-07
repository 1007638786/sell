package com.imooc.sell.service.impl;

import com.imooc.sell.dateobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.service.ProductInfoService;
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
import java.util.UUID;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService infoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = infoService.findOne("9734da5841e8b015998181b");
        Assert.assertEquals("9734da5841e8b015998181b",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = infoService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());

    }

    @Test
    public void findAll() {
        PageRequest request =  PageRequest.of(0,2);

        Page<ProductInfo> pageProductinfo = infoService.findAll(request);
        System.out.println(pageProductinfo.getTotalElements());

    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("11223344");
        info.setProductName("皮蛋瘦肉粥");
        info.setProductPrice(new BigDecimal(5.5));
        info.setProductStock(100);
        info.setProductDescription("鲜香可口");
        info.setProductIcon("tupiantupaindizhitupaindizhi");
        info.setProductStatus(ProductStatusEnum.UP.getCode());
        info.setCategoryType(1);
        ProductInfo save = infoService.save(info);
        Assert.assertNotNull(save);
    }
}