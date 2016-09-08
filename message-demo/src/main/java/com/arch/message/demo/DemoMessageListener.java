package com.arch.message.demo;

import com.arch.message.metaq.Message;
import com.arch.message.metaq.MessageListener;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public class DemoMessageListener implements MessageListener {
    public void onMessage(Message message) {
        System.out.println(message.getContent());
    }
}
