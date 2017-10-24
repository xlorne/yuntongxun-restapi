package com.lorne.yuntongxun;

import com.lorne.yuntongxun.service.YtxMsgService;
import com.lorne.yuntongxun.service.impl.YtxMsgServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * create by lorne on 2017/9/25
 */
@Configuration
@ComponentScan
public class YunTongXunConfiguration {


    public static void main(String[] args) {
        YtxMsgService ytxMsgService = new YtxMsgServiceImpl();
        ytxMsgService.sendMsg("43273","15562581350","123");
    }
}
