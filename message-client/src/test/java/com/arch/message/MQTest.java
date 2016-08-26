package com.arch.message;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class MQTest {

    @Test
    public void testConsumer() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "rmq-group");

        consumer.setNamesrvAddr("192.168.66.77:9876");
        consumer.setInstanceName("rmq-instance");
        consumer.subscribe("TopicA-test", "TagA");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }

    @Test
    public void testProducer() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("192.168.66.77:9876");
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            for (int i = 0; i < 3; i++) {
                Message msg = new Message("TopicA-test",// topic
                        "TagA",// tag
                        (new Date() + "Hello RocketMQ ,QuickStart" + i)
                                .getBytes()// body
                );
                System.out.println("Producer Message: " + msg);
                SendResult sendResult = producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

}
