package com.arch.message.metaq.consumer;


import com.arch.message.metaq.MessageListener;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public interface ConsumerFactory {

    Consumer createConsumer(String topic, String consumerId, ConsumerConfig consumerConfig, MessageListener messageListener) throws Exception;
}
