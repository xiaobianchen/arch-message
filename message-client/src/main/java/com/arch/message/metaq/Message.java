package com.arch.message.metaq;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public interface Message {

    String getContent();

    <T> T getContent(Class<T> clazz);
}
