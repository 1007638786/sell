package com.imooc.sell.controller;

import com.imooc.sell.VO.CategoryVo;
import com.imooc.sell.VO.InfoVo;
import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.dateobject.ProductCategory;
import com.imooc.sell.dateobject.ProductInfo;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductCategoryService categoryService;

//查询全部上架的商品   数据拼接    写utils方法封装result
    @GetMapping("list")
    public ResultVo  getProductList(){
        List<ProductInfo> upAll = infoService.findUpAll();
       List<Integer> CategoryTypeList=  new ArrayList<>();
        for (ProductInfo info :upAll ) {
            CategoryTypeList.add(info.getCategoryType());
        }
        List<ProductCategory> ProductCategoryList = categoryService.findByCategoryTypeIn(CategoryTypeList);
        List<CategoryVo> categoryVos = new ArrayList<>();
        for (ProductCategory category: ProductCategoryList) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setCategoryName(category.getCategoryName());
            categoryVo.setCategoryType(category.getCategoryType());
            ArrayList<InfoVo> infoVos = new ArrayList<>();
            for(ProductInfo info: upAll){
                if(info.getCategoryType().equals(category.getCategoryType())){
                    InfoVo infoVo = new InfoVo();
                    infoVo.setInfoId(info.getProductId());
                    infoVo.setInfoDescription(info.getProductDescription());
                    infoVo.setInfoIcon(info.getProductIcon());
                    infoVo.setInfoName(info.getProductName());
                    infoVo.setInfoPrice(info.getProductPrice());
                    infoVos.add(infoVo);
                }
            }
            categoryVo.setInfoVos(infoVos);
            categoryVos.add(categoryVo);
        }

        return ResultVoUtil.success(categoryVos);
    };

}
