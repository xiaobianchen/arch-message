package com.arch.message.rocketmq;

import com.arch.message.rocketmq.impl.ConsumerFactoryImpl;
import com.arch.message.rocketmq.impl.ProducerFactoryIml;
import org.junit.Test;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class MQTest {

    @Test
    public void testSend(){
        ProducerFactory producerFactory = new ProducerFactoryIml();
        producerFactory.sendMessage();
    }

    @Test
    public void testReceive(){
        ConsumerFactory consumerFactory = new ConsumerFactoryImpl();
        consumerFactory.onMessage();
    }


}
