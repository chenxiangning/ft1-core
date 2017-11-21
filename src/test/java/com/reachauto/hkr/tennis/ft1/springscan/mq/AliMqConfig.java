package com.reachauto.hkr.tennis.ft1.springscan.mq;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.OrderConsumerBean;
import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/11/21 21:13
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Configuration
public class AliMqConfig {

    @Bean("propertiesFactoryBean")
    public PropertiesFactoryBean propertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Properties properties = new Properties();

        properties.setProperty("Topic", "HKR-CXN-TOPIC");
        properties.setProperty(PropertyKeyConst.ProducerId, "PID-CXN");
        properties.setProperty(PropertyKeyConst.ConsumerId, "CID-CXN");
        properties.setProperty(PropertyKeyConst.AccessKey, "jN9LHU8IqjXYcRl2");
        properties.setProperty(PropertyKeyConst.SecretKey, "Tl67HEwJKvvnlw2B0MMihNKFVjVyaz");
        properties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        propertiesFactoryBean.setProperties(properties);
        return propertiesFactoryBean;
    }


    @Bean("orderProducerBean")
    public OrderProducerBean orderProducerBean(@Autowired @Qualifier("propertiesFactoryBean") Properties properties) {
        OrderProducerBean orderProducerBean = new OrderProducerBean();
        orderProducerBean.setProperties(properties);
        orderProducerBean.start();

        System.out.println("orderProducerBean.start######");

        return orderProducerBean;
    }


    @Bean("orderConsumerBean")
    public OrderConsumerBean orderConsumerBean(@Autowired @Qualifier("propertiesFactoryBean") Properties properties,
                                               @Autowired @Qualifier("messageListener") MessageListener messageListener) {
        OrderConsumerBean orderConsumerBean = new OrderConsumerBean();

        orderConsumerBean.setProperties(properties);

        Subscription subscription = new Subscription();
        subscription.setTopic(properties.getProperty("Topic"));
        subscription.setExpression("*");

        Map<Subscription, MessageOrderListener> map = new HashMap();
        map.put(subscription, messageListener);
        orderConsumerBean.setSubscriptionTable(map);

        orderConsumerBean.start();
        System.out.println("orderConsumerBean.start$$$$$");
        return orderConsumerBean;

    }


}
