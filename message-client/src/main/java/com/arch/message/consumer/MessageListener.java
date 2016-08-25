package com.arch.message.consumer;

import com.arch.message.exception.SendFailedException;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public interface MessageListener {

    void onMessage() throws SendFailedException;
}
