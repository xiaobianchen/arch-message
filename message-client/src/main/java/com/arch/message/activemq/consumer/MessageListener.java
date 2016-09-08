package com.arch.message.activemq.consumer;

import com.arch.message.exception.SendFailedException;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public interface MessageListener {

    void onMessage() throws SendFailedException;
}
