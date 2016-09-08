package com.arch.message.metaq.producer.impl;


import com.arch.message.constants.Constants;
import com.arch.message.exception.SendFailedException;
import com.arch.message.metaq.producer.Producer;
import com.arch.message.metaq.producer.ProducerFactory;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.JSONUtils;
import com.taobao.metamorphosis.utils.ZkUtils;

/**
 * Created by chenxiaobian on  16/9/8.
 */
public class ProducerFactoryImpl implements ProducerFactory {
    private static ProducerFactoryImpl instance = new ProducerFactoryImpl();
    private final MetaMessageSessionFactory sessionFactory;

    public static ProducerFactory getInstance() {
        return instance;
    }

    private ProducerFactoryImpl() {
        MetaClientConfig metaClientConfig = new MetaClientConfig();
        metaClientConfig.setZkConfig(new ZkUtils.ZKConfig());
        metaClientConfig.setZkConnect(Constants.Metaq_Zk_Server_Key);
        try {
            this.sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        } catch (MetaClientException e) {
            throw new RuntimeException(e);
        }
    }

    public Producer createProducer(final String topic) throws Exception {

        final MessageProducer producer = sessionFactory.createProducer();
        producer.publish(topic);
        return new Producer() {
            public void sendMessage(Object obj) throws SendFailedException {
                try {
                    producer.sendMessage(new Message(topic, JSONUtils.serializeObject(obj).getBytes()));
                } catch (Throwable t) {
                    throw new SendFailedException(t);
                } finally {
                }
            }
        };
    }
}
