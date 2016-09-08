package com.arch.message.activemq.consumer.producer.impl;

import com.arch.message.constants.Constants;
import com.arch.message.exception.SendFailedException;
import com.arch.message.activemq.consumer.producer.Producer;
import com.arch.message.activemq.consumer.producer.ProducerFactory;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public class ProducerFactoryImpl implements ProducerFactory, Producer {

    public void createProducer() throws SendFailedException {
        Connection connection = null;
        Session session = null;
        try {
            // 创建链接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, Constants.BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(Constants.DESTINATION);
            // 创建消息制作者
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessage(session, producer);
            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw new SendFailedException("send message failed", e);
        } finally {

            try {
                // 关闭释放资源
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for(int i = 0; i < Constants.SEND_NUM;i++){
            String content = "发送消息第" + (i + 1) + "条";
            TextMessage text = session.createTextMessage(content);

            System.out.println(content);
            messageProducer.send(text);
        }
    }
}
