package com.arch.message.producer;

import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public interface Producer {

    void sendMessage(Session session, MessageProducer messageProducer) throws Exception;
}
