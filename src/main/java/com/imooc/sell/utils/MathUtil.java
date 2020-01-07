package com.imooc.sell.utils;

public class MathUtil {

    public static final double  MONEY_RANGE=0.01;

    public static  boolean  payva(Double a , Double b ){
        return Math.abs(a-b)<0.01;
    }
}
