package com.lorne.yuntongxun.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.lorne.core.framework.utils.DateUtil;
import com.lorne.core.framework.utils.config.ConfigUtils;
import com.lorne.core.framework.utils.encode.Base64Utils;
import com.lorne.core.framework.utils.encode.MD5Util;

import com.lorne.yuntongxun.service.YtxMsgService;
import com.lorne.yuntongxun.utils.HttpUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

/**
 * create by lorne on 2017/9/25
 */
@Service
public class YtxMsgServiceImpl implements YtxMsgService {


    private Logger logger = LoggerFactory.getLogger(YtxMsgServiceImpl.class);

    private final static String TemplateSMSUrl = "/2013-12-26/Accounts/{accountSid}/SMS/TemplateSMS?sig={SigParameter}";

    private static String url;
    private static String accountSid;
    private static String accountToken;
    private static String appId;


    public YtxMsgServiceImpl() {
        url = ConfigUtils.getString("yuntongxun.properties","url");
        accountSid = ConfigUtils.getString("yuntongxun.properties","accountSid");
        accountToken = ConfigUtils.getString("yuntongxun.properties","accountToken");
        appId = ConfigUtils.getString("yuntongxun.properties","appId");
    }



    @Override
    public boolean sendMsg(String msgId, String mobiles, String... args) {
        String msgUrl = url+TemplateSMSUrl;
        msgUrl = msgUrl.replace("{accountSid}",accountSid);
        String timer = DateUtil.formatDate(new Date(),DateUtil.LOCATE_DATE_FORMAT);

        String sigParameter;
        try {
            sigParameter = MD5Util.md5((accountSid+accountToken+timer).getBytes("utf-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return false;
        }

        msgUrl = msgUrl.replace("{SigParameter}",sigParameter);
        String authorization;
        try {
            authorization = Base64Utils.encode( (accountSid+":"+timer).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return false;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to",mobiles);
        jsonObject.put("appId",appId);
        jsonObject.put("templateId",msgId);
        jsonObject.put("datas", Arrays.asList(args));
        String json = jsonObject.toJSONString();

        logger.info("url->"+msgUrl);
        logger.info("json->"+json);
        logger.info("authorization->"+authorization);
        String res = HttpUtils.postJson(msgUrl,json,authorization);
        logger.info("res->"+res);
        if(StringUtils.isEmpty(res)){
            logger.error("res is null.");
            return false;
        }
        JSONObject resJson = JSON.parseObject(res);
        if("000000".equals(resJson.getString("statusCode"))){
            return true;
        }
        return false;
    }



}
