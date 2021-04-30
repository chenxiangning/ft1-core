package com.hkr.architecture.tennis.springscan.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.hkr.architecture.tennis.notscan.mq.FT1AliMqProperties;
import com.hkr.architecture.tennis.notscan.mq.Ft1AliMQMessage;
import com.hkr.architecture.tennis.notscan.mq.Ft1AliMqFactory;
import com.hkr.architecture.tennis.notscan.mq.msg.PMSParameter;
import com.hkr.architecture.tennis.notscan.mq.msg.SMSParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 23:23
 * To change this template use File | Settings | File Templates.
 * chenxiangning@rental.com
 */
@RestController
public class MqController {

    static int num;

    @RequestMapping("/mq1")
    @ResponseBody
    public String demo() {

        PMSParameter pushParameter = new PMSParameter();

        //循环发送消息
        for (int i = 0; i < 1000; i++) {
            pushParameter = new PMSParameter();
            pushParameter.setDeviceNo("dd3a89a19aae8de4d9ddcaf270dac244bd6a617aa8eab3693191020a6ae5");
            pushParameter.setDeviceType("2");
            pushParameter.setMessage("陈湘宁测试-"+i);
            pushParameter.setMessageType("1");
            pushParameter.setUserId("16");

            Message msg = Ft1AliMQMessage.buildHkrMgMessage(pushParameter.json(), FT1AliMqProperties.TagMsgPMS);
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
//            msg.setKey("ORDERID_100");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = Ft1AliMqFactory.ft1MessageProducer().send(msg, "Hkr-message-key");
                assert sendResult != null;
//                System.out.println(sendResult);
            } catch (ONSClientException e) {
                System.out.println("发送失败" + e.getMessage());
                //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
            }
        }

        return "hello world mq TagMsgPMS";
    }

    @RequestMapping("/mq2")
    @ResponseBody
    public String demo2() {
        SMSParameter sendSMSParameter;


        //循环发送消息
        for (int i = 0; i < 1; i++) {
            sendSMSParameter = new SMSParameter();
            sendSMSParameter.setPhoneNo("15524542520");
            sendSMSParameter.setSignName("氢氪出行");
            sendSMSParameter.setTemplateCode("SMS_7500515");
            sendSMSParameter.setTemplateVal("{'code':'1234'}");



            Message msg = Ft1AliMQMessage.buildHkrMgMessage(sendSMSParameter.json(), FT1AliMqProperties.TagMsgSMS);
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("ORDERID_100");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = Ft1AliMqFactory.ft1MessageProducer().send(msg, "Hkr-message-key                  ");
                assert sendResult != null;
                System.out.println(sendResult);
            } catch (ONSClientException e) {
                System.out.println("发送失败" + e.getMessage());
                //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
            }
        }

        return "hello world mq TagMsgSMS";
    }


}
