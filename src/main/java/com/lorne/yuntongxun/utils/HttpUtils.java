package com.lorne.yuntongxun.utils;

import com.lorne.core.framework.utils.http.HttpClientFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;


/**
 * create by lorne on 2017/10/24
 */
public class HttpUtils {

    public static String postJson(String url, String json, String authorization) {
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        HttpPost request = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, "utf-8");
        request.addHeader("Authorization",authorization);
        request.addHeader("Accept","application/json");
        request.addHeader("Content-Type","application/json;charset=utf-8");
        request.setEntity(stringEntity);
        return com.lorne.core.framework.utils.http.HttpUtils.execute(httpClient, request);
    }
}
