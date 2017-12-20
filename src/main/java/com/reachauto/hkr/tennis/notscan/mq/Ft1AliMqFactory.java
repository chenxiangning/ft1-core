package com.reachauto.hkr.tennis.notscan.mq;

import com.aliyun.openservices.ons.api.bean.OrderConsumerBean;
import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-22 13:27
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: xx
 */
public class Ft1AliMqFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ft1AliMqFactory.class);

    private static OrderProducerBean ft1MessageProducer;
    private static OrderConsumerBean ft1MessageConsumer;

    private static Properties ft1MessageProperties;

    private Ft1AliMqFactory() {
    }

    public static OrderProducerBean initFt1MessageProducer(Properties properties) {
        ft1MessageProperties = properties;

        ft1MessageProducer = new OrderProducerBean();
        ft1MessageProducer.setProperties(properties);
        if ("true".equals(properties.getProperty(FT1AliMqProperties.OpenTheLogo))) {
            ft1MessageProducer.start();
            LOGGER.info("ft1MessageProducer. ##开始启动##");
            LOGGER.info("消息发送mq生产者配置信息 {}", properties.toString());
        } else {
            LOGGER.info("ft1MessageProducer. ##未启动##");
        }

        return ft1MessageProducer;
    }

    /**
     * @param properties           配置信息
     * @param tags                 多个tag之间用||分割,如果全部tag,用*替代.
     * @param messageOrderListener 监控处理程序
     */
    public static void initFt1MessageConsumer(Properties properties, String tags, MessageOrderListener messageOrderListener) {
        ft1MessageProperties = properties;
        ft1MessageConsumer = new OrderConsumerBean();

        ft1MessageConsumer.setProperties(properties);

        Subscription subscription = new Subscription();
        subscription.setTopic(properties.getProperty(FT1AliMqProperties.Topic));
        subscription.setExpression(tags);

        Map<Subscription, MessageOrderListener> map = new HashMap();
        map.put(subscription, messageOrderListener);
        ft1MessageConsumer.setSubscriptionTable(map);

        if ("true".equals(properties.getProperty(FT1AliMqProperties.OpenTheLogo))) {
            ft1MessageConsumer.start();
            LOGGER.info("ft1MessageConsumer. ##开始启动##");
            LOGGER.info("消息消费者启动信息 properties {} ", properties.toString());
            LOGGER.info("消息消费者启动信息 tags {} ListenerClass {}", tags, messageOrderListener.getClass());
        } else {
            LOGGER.info("ft1MessageConsumer. ##未启动##");
        }

    }

    public static OrderProducerBean ft1MessageProducer() {
        return ft1MessageProducer;
    }

    public static OrderConsumerBean ft1MessageConsumer() {
        return ft1MessageConsumer;
    }

    public static Properties ft1MessageProperties() {
        return ft1MessageProperties;
    }
}