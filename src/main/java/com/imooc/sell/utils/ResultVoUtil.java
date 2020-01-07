package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVo;

import java.util.List;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return  resultVo;
    }
    public static ResultVo success(){
        return  ResultVoUtil.success(null);
    }

    public static ResultVo error(Integer code,String message){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return  resultVo;
    }
}
