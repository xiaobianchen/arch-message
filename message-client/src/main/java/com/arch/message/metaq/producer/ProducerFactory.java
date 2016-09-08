package com.arch.message.metaq.producer;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public interface ProducerFactory  {

    /**
     * 获取默认配置的Producer实例,目前只支持同步发送消息
     */
    Producer createProducer(String topic) throws Exception;

}
