package com.lorne.yuntongxun.service.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.lorne.core.framework.utils.config.ConfigUtils;
import com.lorne.yuntongxun.service.YtxMsgService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create by lorne on 2017/9/25
 */
@Service
public class YtxMsgServiceImpl implements YtxMsgService {

    private CCPRestSDK restAPI;


    public YtxMsgServiceImpl() {
        restAPI = new CCPRestSDK();
        String ip = ConfigUtils.getString("yuntongxun.properties","ip");
        String port = ConfigUtils.getString("yuntongxun.properties","port");
        String accountSid = ConfigUtils.getString("yuntongxun.properties","accountSid");
        String accountToken = ConfigUtils.getString("yuntongxun.properties","accountToken");
        String appId = ConfigUtils.getString("yuntongxun.properties","appId");

        restAPI.init(ip, port);
        restAPI.setAccount(accountSid, accountToken);
        restAPI.setAppId(appId);
    }



    @Override
    public boolean sendMsg(String msgId, String mobiles, String... args) {
        Map<String, Object> result = restAPI.sendTemplateSMS(mobiles,msgId,args);
        if(result!=null&&"000000".equals(result.get("statusCode"))){
            return true;
        }
        return false;
    }



}
