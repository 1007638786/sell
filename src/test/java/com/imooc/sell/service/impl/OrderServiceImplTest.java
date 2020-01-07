package com.imooc.sell.service.impl;

import com.imooc.sell.dateobject.OrderDetail;
import com.imooc.sell.dateobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    private static  String OPEN_ID_ALL="wcfpyj";

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("wcf");
        orderDTO.setBuyerPhone("13303970996");
        orderDTO.setBuyerAddress("小潘的宿舍");
        orderDTO.setBuyerOpenid(OPEN_ID_ALL);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("11223344");
        o1.setProductQuantity(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("9734da5841e8b015998181b");
        o2.setProductQuantity(2);
        orderDetails.add(o1);
        orderDetails.add(o2);
        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
         System.out.println(orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO one = orderService.findOne("1575891182252702619");
        System.out.println(one);
    }

    @Test
    public void findList() {
        PageRequest pageRequest =  PageRequest.of(0, 10);
        Page<OrderDTO> page = orderService.findList(OPEN_ID_ALL, pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO one = orderService.findOne("1575890662372817342");
        OrderDTO cancel = orderService.cancel(one);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO one = orderService.findOne("1575891182252702619");
        OrderDTO cancel = orderService.finish(one);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO one = orderService.findOne("1575891182252702619");
        OrderDTO paid = orderService.paid(one);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),paid.getPayStatus());
    }
    @Test
    public void findlist(){
        PageRequest pageRequest =  PageRequest.of(0, 10);
        Page<OrderDTO> page = orderService.findList(pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());
    }
}