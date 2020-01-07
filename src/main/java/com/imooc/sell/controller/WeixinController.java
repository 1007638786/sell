package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("weixin")
public class WeixinController {

    @RequestMapping("auth")
    public void  auth (String code) {
    String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0259d8f25f9cbfe0&secret=3d3e2c40c217132d31642d473c7b80a3&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate =new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info(result);

    }
}
