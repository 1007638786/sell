package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WeChatPayConfig {

    @Autowired
    private WeChatPayAccountConfig weChatPayAccountConfig;

    @Bean
    public BestPayServiceImpl  bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weChatPayAccountConfig.getMpAppId());
        wxPayH5Config.setKeyPath(weChatPayAccountConfig.getKeyPath());
        wxPayH5Config.setMchId(weChatPayAccountConfig.getMchId());
        wxPayH5Config.setMchKey(weChatPayAccountConfig.getMchKey());
        wxPayH5Config.setNotifyUrl(weChatPayAccountConfig.getNotifyUrl());

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return  bestPayService;
    }
}
