package com.arch.message;


import com.arch.message.activemq.consumer.impl.ConsumerFactoryImpl;
import com.arch.message.activemq.consumer.producer.ProducerFactory;
import com.arch.message.activemq.consumer.producer.impl.ProducerFactoryImpl;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testSendMessage(){
        ProducerFactory producerFactory = new ProducerFactoryImpl();
        producerFactory.createProducer();
    }

    @Test
    public void testReceiveMessage(){
        ConsumerFactoryImpl consumerFactory = new ConsumerFactoryImpl();
        consumerFactory.onMessage();

    }

}
