package com.arch.message.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.arch.message.constants.Constants;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class Consumer {

    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int inititalState = 0;

    private Consumer(){

    }

    public static DefaultMQPushConsumer getInstance(){
        if(consumer == null){
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        }

        if(inititalState == 0){
            consumer.setNamesrvAddr(Constants.NAMESRVADDR);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            inititalState = 1;
        }

        return consumer;
    }

}
