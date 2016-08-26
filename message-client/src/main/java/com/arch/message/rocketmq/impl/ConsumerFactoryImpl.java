package com.arch.message.rocketmq.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.arch.message.exception.SendFailedException;
import com.arch.message.rocketmq.Consumer;
import com.arch.message.rocketmq.ConsumerFactory;

import java.util.List;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class ConsumerFactoryImpl implements ConsumerFactory {

    public void onMessage() throws SendFailedException {
        // 获取消息生产者
        DefaultMQPushConsumer consumer = Consumer.getInstance();

        // 订阅主体
        try {
            consumer.subscribe("TopicTest1", "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {

                /**
                 * * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                    MessageExt msg = msgs.get(0);

                    if (msg.getTopic().equals("TopicTest1")) {
                        // 执行TopicTest1的消费逻辑
                        if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                            // 执行TagA的消费
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagC")) {
                            // 执行TagC的消费
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagD")) {
                            // 执行TagD的消费
                        }
                    } else if (msg.getTopic().equals("TopicTest2")) {
                        // 执行TopicTest2的消费逻辑
                    }

                    System.out.println(msg);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /**
             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             */
            consumer.start();

            System.out.println(("Consumer Started."));
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
