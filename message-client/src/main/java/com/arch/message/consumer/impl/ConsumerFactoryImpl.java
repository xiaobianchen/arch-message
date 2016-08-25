package com.arch.message.consumer.impl;

import com.arch.message.constants.Constants;
import com.arch.message.consumer.MessageListener;
import com.arch.message.exception.SendFailedException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public class ConsumerFactoryImpl implements MessageListener {

    public void onMessage() throws SendFailedException {

        Connection connection = null;
        Session session = null;

        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, Constants.BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(Constants.DESTINATION);
            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                // 接收数据的时间（等待） 10 ms
                Message message = consumer.receive(1000 * 10);

                TextMessage text = (TextMessage) message;
                if (text != null) {
                    System.out.println("接收：" + text.getText());
                } else {
                    break;
                }
            }

            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw new SendFailedException("receive message failed", e);
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
                throw new SendFailedException("message failed", e);
            }
        }


    }
}
