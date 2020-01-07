package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    @JsonProperty("name")
    private  String  categoryName;
    @JsonProperty("type")
    private  Integer categoryType;
    @JsonProperty("foods")
    private List<InfoVo>   infoVos;
}
