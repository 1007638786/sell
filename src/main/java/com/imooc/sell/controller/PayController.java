package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.rest.type.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService  payService;



    @RequestMapping("create")
    public ModelAndView creat(@RequestParam("orderId")String orderId,
                              @RequestParam("returnUrl")String returnUrl,
                              Map<String,Object> map){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            log.error("【微信订单发起】订单不存在,orderId={}",orderId);
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse reponse = payService.creat(orderDTO);

        map.put("payResonse",reponse);
        map.put("returnUrl", URLDecoder.decode(returnUrl));


        return  new ModelAndView("pay/create",map);
    }
    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData){
        PayResponse notify = payService.notify(notifyData);
        return  new ModelAndView("pay/success");
    }




}
