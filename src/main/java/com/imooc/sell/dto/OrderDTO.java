package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dateobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.serializer.Date2LongSerializer;
import com.imooc.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.criterion.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    //买家名称
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态 默认0 新下单
    private Integer  orderStatus;
    //支付状态 默认0 待支付
    private Integer payStatus;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date  updateTime;

    private List<OrderDetail> orderDetailList;


    @JsonIgnore
    public    OrderStatusEnum  getOrderStatusEnum(){
        return   EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public    PayStatusEnum  getPayStatusEnum(){
        return     EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }


}
