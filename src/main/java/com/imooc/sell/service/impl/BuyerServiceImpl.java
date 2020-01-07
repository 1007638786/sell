package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderone(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        return orderDTO;
    }

    @Override
    public OrderDTO canserOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
            if(orderDTO==null){
                log.error("【取消订单】查不到该订单,orderId={}",orderId);
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO one = orderService.findOne(orderId);
        if(one==null){
            return  null;
        }
        if (!openid.equalsIgnoreCase(one.getBuyerOpenid())){
              log.error("【查询订单】openid不一致");
              throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return one;
    }



}
