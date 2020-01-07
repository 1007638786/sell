package com.imooc.sell.dateobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
//动态更新注解
@DynamicUpdate
//lombok插件自动生成getsettostring 简化代码节省开发时间
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date   createTime;

    private Date   updateTime;


}


