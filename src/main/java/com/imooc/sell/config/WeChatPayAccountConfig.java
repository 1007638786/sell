package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

@Data
@ConfigurationProperties(prefix = "wechatpay")
@Component
public class WeChatPayAccountConfig {
    /**
     * 公众号appId
     */
    private String mpAppId;

    /**
     * 公众号appSecret
     */
    private String appSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 证书内容
     */
    private SSLContext sslContext;
    /**
     * 微信异步通知
     */
    private String  notifyUrl;
}
