package com.reachauto.hkr.tennis.ft1.springscan.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import com.google.common.util.concurrent.RateLimiter;
import com.reachauto.hkr.tennis.ft1.notscan.gson.GsonTool;
import com.reachauto.hkr.tennis.ft1.notscan.mq.FT1AliMqProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/11/21 22:20
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class CidMSGListener implements MessageOrderListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CidMSGListener.class);

    RateLimiter limiter = RateLimiter.create(100D);
    /**
     * 消费消息接口，由应用来实现<br>
     * 需要注意网络抖动等不稳定的情形可能会带来消息重复，对重复消息敏感的业务可对消息做幂等处理
     *
     * @param message 消息
     * @param context 消费上下文
     * @return {@link OrderAction} 消费结果，如果应用抛出异常或者返回Null等价于返回Action.ReconsumeLater
     * @see <a href="https://help.aliyun.com/document_detail/44397.html">如何做到消费幂等</a>
     */
    @Override
    public OrderAction consume(Message message, ConsumeOrderContext context) {
        try {
            limiter.acquire();
            LOGGER.info("{}",message.getTag());
            if (FT1AliMqProperties.TagMsgPMS.equals(message.getTag())) {
                pms(message);
            }
            if (FT1AliMqProperties.TagMsgSMS.equals(message.getTag())) {
                sms(message);
            }
            return OrderAction.Success;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return OrderAction.Suspend;
        }

    }

    private void sms(Message message) throws UnsupportedEncodingException {
        SendSMSParameter sendSMSParameter = GsonTool.jsonToBean(new String(message.getBody(), "utf-8"), SendSMSParameter.class);
        LOGGER.info("{}", sendSMSParameter);
    }

    private void pms(Message message) throws UnsupportedEncodingException {
        UmengPushParameter pushParameter = GsonTool.jsonToBean(new String(message.getBody(), "utf-8"), UmengPushParameter.class);
        LOGGER.info("{}", pushParameter);
    }
}
