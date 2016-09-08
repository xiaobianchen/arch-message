package com.arch.message.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        Thread.sleep(100000000l);
    }
}
