package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
}
