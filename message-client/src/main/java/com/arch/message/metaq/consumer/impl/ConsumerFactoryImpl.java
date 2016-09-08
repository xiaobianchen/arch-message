package com.arch.message.metaq.consumer.impl;


import com.arch.message.constants.Constants;
import com.arch.message.metaq.MessageListener;
import com.arch.message.metaq.consumer.Consumer;
import com.arch.message.metaq.consumer.ConsumerConfig;
import com.arch.message.metaq.consumer.ConsumerFactory;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.JSONUtils;
import com.taobao.metamorphosis.utils.ZkUtils;

import java.util.concurrent.*;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public class ConsumerFactoryImpl implements ConsumerFactory {

    private static ConsumerFactoryImpl instance = new ConsumerFactoryImpl();
    private final MetaMessageSessionFactory sessionFactory;

    public static ConsumerFactory getInstance() {
        return instance;
    }

    private ConsumerFactoryImpl() {
        MetaClientConfig metaClientConfig = new MetaClientConfig();
        metaClientConfig.setZkConfig(new ZkUtils.ZKConfig());
        metaClientConfig.setZkConnect(Constants.Metaq_Zk_Server_Key);

        try {
            this.sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        } catch (MetaClientException e) {
            throw new RuntimeException(e);
        }
    }


    public Consumer createConsumer(final String topic, final String consumerId, final ConsumerConfig config, final MessageListener messageListener) throws Exception {
        com.taobao.metamorphosis.client.consumer.ConsumerConfig tbConsumerConfig = new com.taobao.metamorphosis.client.consumer.ConsumerConfig(consumerId);
        tbConsumerConfig.setConsumeFromMaxOffset();
        tbConsumerConfig.setMaxFetchRetries(config.getRetryCount());
        tbConsumerConfig.setMaxDelayFetchTimeInMills(config.getMaxDelayFetchTimeInMills());

        final ThreadPoolExecutor executor = new ThreadPoolExecutor(config.getThreadPoolSize(),
                config.getThreadPoolSize(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        final MessageConsumer consumer = sessionFactory.createConsumer(tbConsumerConfig);

        consumer.subscribe(topic, 1024 * 1024, new com.taobao.metamorphosis.client.consumer.MessageListener() {

            public void recieveMessages(Message message) {
                final String content = new String(message.getData());

                try {
                    messageListener.onMessage(new com.arch.message.metaq.Message() {
                        public String getContent() {
                            return content;
                        }

                        public <T> T getContent(Class<T> clazz) {
                            try {
                                return (T) JSONUtils.deserializeObject(content, clazz);
                            } catch (Exception e) {
                                throw new RuntimeException("cat not convert to class " + clazz.getSimpleName(), e);
                            }
                        }
                    });
                } catch (RuntimeException t) {
                    throw t;
                } catch (Throwable t) {
                    throw new RuntimeException(t);
                } finally {
                }

            }

            public Executor getExecutor() {
                //队列大小需要限制，不然会内存泄露
                return executor;
            }


        });

        return new Consumer() {
            public String getTopic() {
                return topic;
            }

            public String getConsumerId() {
                return consumerId;
            }

            public void start() {
                try {
                    consumer.completeSubscribe();
                } catch (MetaClientException e) {
                    throw new RuntimeException(e);
                }
            }


            public MessageListener getListener() {
                return messageListener;
            }

            public void close() {
                try {
                    consumer.shutdown();
                } catch (MetaClientException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
