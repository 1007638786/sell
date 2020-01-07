package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

public interface BuyerService {
    //查询订单详情
    OrderDTO  findOrderone(String openid,String orderId);
    //取消订单
    OrderDTO  canserOrder(String openid,String orderId);
}
