package com.reachauto.hkr.tennis.ft1.springscan.mq;

import com.reachauto.hkr.tennis.ft1.notscan.mq.FT1AliMqProperties;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean("ft1AliMqMessageProperties")
    public PropertiesFactoryBean propertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Properties properties = new Properties();

//        properties.setProperty(FT1AliMqProperties.Topic, "HKR-TEST-FT1-MSG-TOPIC");
//        properties.setProperty(FT1AliMqProperties.ProducerId, "PID-TEST-FT1-MSG");
//        properties.setProperty(FT1AliMqProperties.ConsumerId, "CID-TEST-FT1-MSG");

        properties.setProperty(FT1AliMqProperties.Topic, "HKR-CXN-TOPIC");
        properties.setProperty(FT1AliMqProperties.ProducerId, "PID-CXN");
        properties.setProperty(FT1AliMqProperties.ConsumerId, "CID-CXN");
        properties.setProperty(FT1AliMqProperties.AccessKey, "jN9LHU8IqjXYcRl2");
        properties.setProperty(FT1AliMqProperties.SecretKey, "Tl67HEwJKvvnlw2B0MMihNKFVjVyaz");
        properties.setProperty(FT1AliMqProperties.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        properties.setProperty(FT1AliMqProperties.OpenTheLogo, String.valueOf(true));

        propertiesFactoryBean.setProperties(properties);

        return propertiesFactoryBean;
    }

}
