package com.hkr.architecture.tennis.notscan.mq;

import com.aliyun.openservices.ons.api.Message;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-22 13:00
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
public class Ft1AliMQMessage {

    /**
     * 消息发送的message
     * @param jsonBody 消息体与mg中提供的接口一致
     * @param typeByTag  AliMqProperties.TagMsgPMS  AliMqProperties.TagMsgSMS
     * @return m
     */
    public static Message buildHkrMgMessage(String jsonBody, String typeByTag) {

        Message msg = new Message(
                // Message所属的Topic
                Ft1AliMqFactory.ft1MessageProperties().getProperty(FT1AliMqProperties.Topic),
                // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                typeByTag,
                // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                jsonBody.getBytes());
        return msg;
    }


}
