package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 */
@Data
public class InfoVo {
    @JsonProperty("id")
    private String  infoId;
    @JsonProperty("name")
    private String  infoName;
    @JsonProperty("price")
    private BigDecimal infoPrice;
    @JsonProperty("description")
    private String  infoDescription;
    @JsonProperty("icon")
    private String  infoIcon;

}
