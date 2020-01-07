package com.imooc.sell.service.impl;

import com.imooc.sell.dateobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
   private ProductInfoRepository  infoRepository;

    @Override
    public ProductInfo findOne(String id) {
        return infoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> list) {
        for (CartDTO car:list) {
            ProductInfo info = infoRepository.findById(car.getProductId()).orElse(null);
            if(info==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer i = info.getProductStock() + car.getProductQuantity();
            info.setProductStock(i);
            infoRepository.save(info);

        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO car:cartDTOList) {
            ProductInfo info = infoRepository.findById(car.getProductId()).orElse(null);
            if(info==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer i = info.getProductStock() - car.getProductQuantity();
            if(i<0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            info.setProductStock(i);
            infoRepository.save(info);
        }

    }
}
