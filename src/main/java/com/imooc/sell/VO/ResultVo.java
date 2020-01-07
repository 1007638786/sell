package com.imooc.sell.VO;

import lombok.Data;

import java.util.List;

/*
* http最外层返回
*
* */
@Data
public class ResultVo<T> {
    private Integer code;
    private String  msg;
    private T data;


}
