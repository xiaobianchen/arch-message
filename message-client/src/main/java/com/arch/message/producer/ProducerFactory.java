package com.arch.message.producer;

import com.arch.message.exception.SendFailedException;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public interface ProducerFactory {

    void createProducer() throws SendFailedException;
}
