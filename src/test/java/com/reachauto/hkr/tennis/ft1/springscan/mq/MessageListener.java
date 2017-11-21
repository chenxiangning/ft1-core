package com.reachauto.hkr.tennis.ft1.springscan.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: xiangning
 * Date: 2017/11/21 22:20
 * To change this template use File | Settings | File Templates.
 * chenxiangning@reachauto.com
 */
@Service
public class MessageListener implements MessageOrderListener {
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

        System.out.println(String.format("message[%s] context:[%s]", message.toString(), context.toString()));
        try {
            //do something..
            return OrderAction.Success;
        } catch (Exception e) {
            //消费失败，挂起当前队列
            return OrderAction.Suspend;
        }
    }
}
