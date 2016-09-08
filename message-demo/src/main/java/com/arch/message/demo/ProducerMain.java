package com.arch.message.demo;

import com.arch.message.metaq.producer.Producer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerMain {


    public static void main(String[] args) throws Exception{


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:producer.xml");
        Producer producer = applicationContext.getBean(Producer.class);
        long start=System.currentTimeMillis();
        for(int i=0;i<20;i++){
            producer.sendMessage("aaa");
            System.out.println(i);
        }

        System.out.println(System.currentTimeMillis()-start);
    }
}
