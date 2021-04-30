package com.hkr.architecture.tennis.springscan;

import com.hkr.architecture.tennis.notscan.mq.FT1AliMqProperties;
import com.hkr.architecture.tennis.notscan.mq.Ft1AliMqFactory;
import com.hkr.architecture.tennis.springscan.mq.CidMSGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning@rental.com
 * Date: 2016/4/28
 * Time: 0:06
 * ...............................
 */
@Service
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);

    @Autowired
    @Qualifier("ft1AliMqMessageProperties")
    private PropertiesFactoryBean propertiesFactoryBean;
    @Autowired
    private CidMSGListener cidMSGListener;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("容器初始化:{}", "完毕");

        try {
            Ft1AliMqFactory.initFt1MessageProducer(propertiesFactoryBean.getObject());
            Ft1AliMqFactory.initFt1MessageConsumer(propertiesFactoryBean.getObject(), FT1AliMqProperties.mgMsgTags(), cidMSGListener);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
