package com.arch.message.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.arch.message.constants.Constants;

/**
 * Created by chenxiaobian on 16/8/26.
 */
public class Producer {

    private static DefaultMQProducer instance = null;
    private static int initalState = 0;

    private Producer(){

    }

    public static DefaultMQProducer getInstance(){
        if(instance == null){
            instance = new DefaultMQProducer("ProducerGroupName");
        }

        if(initalState == 0){
            instance.setNamesrvAddr(Constants.NAMESRVADDR);

            try {
                instance.start();
            } catch (MQClientException e) {
                e.printStackTrace();
                return null;
            }
            initalState = 1;
        }

        return instance;

    }
}
