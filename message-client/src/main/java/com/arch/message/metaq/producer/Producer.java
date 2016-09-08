package com.arch.message.metaq.producer;

import com.arch.message.exception.SendFailedException;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public interface Producer {

    void sendMessage(Object obj) throws SendFailedException;

}
