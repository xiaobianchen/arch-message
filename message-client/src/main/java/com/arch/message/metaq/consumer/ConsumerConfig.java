package com.arch.message.metaq.consumer;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public class ConsumerConfig {
    private int threadPoolSize = 1;
    private int retryCount = 5;                                 //重试次数
    private long maxDelayFetchTimeInMills = 2000; //当pull类型时有用，fetch的时间间隔


    public long getMaxDelayFetchTimeInMills() {
        return maxDelayFetchTimeInMills;
    }

    public void setMaxDelayFetchTimeInMills(long maxDelayFetchTimeInMills) {
        this.maxDelayFetchTimeInMills = maxDelayFetchTimeInMills;
    }


    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
