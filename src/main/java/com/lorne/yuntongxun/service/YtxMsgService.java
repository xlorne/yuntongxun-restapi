package com.lorne.yuntongxun.service;

/**
 * 云通讯服务对象
 * create by lorne on 2017/9/25
 */
public interface YtxMsgService {


    boolean sendMsg(String msgId,String mobiles,String ... args);

}
