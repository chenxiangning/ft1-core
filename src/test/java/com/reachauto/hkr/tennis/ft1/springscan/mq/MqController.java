package com.reachauto.hkr.tennis.ft1.springscan.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/9/26 23:23
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@RestController
public class MqController {

    @Autowired
    private OrderProducerBean orderProducerBean;

    @Autowired
    @Qualifier("propertiesFactoryBean")
    private Properties properties;

    @RequestMapping("/mq")
    @ResponseBody
    public String demo() {

        //循环发送消息
        for (int i = 0; i < 100; i++) {
            Message msg = new Message( //
                    // Message所属的Topic
                    properties.getProperty("Topic"),
                    // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    "tag-cxn",
                    // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    "Hello MQ".getBytes());
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("ORDERID_100");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = orderProducerBean.send(msg, "shardingKey");
                assert sendResult != null;
                System.out.println(sendResult);
            } catch (ONSClientException e) {
                System.out.println("发送失败");
                //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
            }
        }

        return "hello world mq";
    }


}
