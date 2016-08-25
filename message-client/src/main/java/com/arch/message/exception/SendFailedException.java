package com.arch.message.exception;

/**
 * Created by chenxiaobian on 16/8/25.
 */
public class SendFailedException extends RuntimeException {

    public SendFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendFailedException(Throwable cause) {
        super(cause);
    }
}
