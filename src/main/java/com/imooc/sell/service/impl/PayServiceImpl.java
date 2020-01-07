package com.imooc.sell.service.impl;


import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.utils.JsonUtil;
import com.imooc.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService  orderService;

    @Override
    public PayResponse creat(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】,payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse pay = bestPayService.pay(payRequest);
        log.info("【微信支付】,PayResponse={}",JsonUtil.toJson(pay));
        return pay;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名（防止别人模拟请求）
        //2.支付状态(判断支付状态 是否为成功)          best pay sdk 完成前两部
        //3. 判断订单金额  和支付过来的金额
        //4. 判断支付的人    下单人和支付人（是否允许下单人和支付人不是同一个人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信异步通知】,PayResponse={}",JsonUtil.toJson(payResponse));
        String orderId = payResponse.getOrderId();
        OrderDTO one = orderService.findOne(orderId);
        if(one ==null){
            log.error("【微信异步通知】订单不存在,orderId={}",orderId);
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //0.1 0.10 都不一样
        if (!MathUtil.payva(payResponse.getOrderAmount(),one.getOrderAmount().doubleValue())){
            log.error("【微信异步通知】订单金额不同,wxAmount={},orderAmount={}",payResponse.getOrderAmount(),one.getOrderAmount());
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //修改订单支付状态
        orderService.paid(one);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO one) {
        RefundRequest request = new RefundRequest();
        request.setOrderId(one.getOrderId());
        request.setOrderAmount(one.getOrderAmount().doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",JsonUtil.toJson(request));
        RefundResponse refund = bestPayService.refund(request);
        log.info("【微信退款】refund={}",JsonUtil.toJson(refund));
        return refund;
    }

}

