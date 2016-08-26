package com.arch.message.rocketmq.impl;


import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.arch.message.rocketmq.Producer;
import com.arch.message.rocketmq.ProducerFactory;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class ProducerFactoryIml implements ProducerFactory {

    public void sendMessage() {
        // 获取消息生产者
        DefaultMQProducer producer = Producer.getInstance();

        try {
            for (int i = 0; i < 2000; i++) {
                Message msg = new Message(
                        "TopicTest1",                   // topic
                        "TagA",                         // tag
                        "OrderID00" + i,                  // key
                        ("Hello MetaQ" + i).getBytes());  // body
                System.out.println("Send Message:" + msg);
                SendResult sendResult = producer.send(msg);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.shutdown();

    }
}
