package com.imooc.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class loggerTest {
    @Test
    public void test(){
         String userName="qwe";
         String password="123456";

        log.debug("aasdasdasd");
        log.error("错了啊啊啊啊啊");
        log.info("userName:{},password:{}",userName,password);
    }
}
