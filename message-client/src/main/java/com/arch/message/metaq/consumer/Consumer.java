package com.arch.message.metaq.consumer;

import com.arch.message.metaq.MessageListener;

/**
 * Created by chenxiaobian on 16/9/8.
 */
public interface Consumer {

    /**
     * 获取消息目的
     *
     * @return
     */
    String getTopic();

    /**
     * 获取consumerId
     *
     * @return
     */
    String getConsumerId();

    /**
     * 启动消费。
     */
    void start();


    /**
     * 获取listener
     *
     * @return
     */
    MessageListener getListener();

    /**
     * 关闭Consumer。关闭后，将与server断开连接，不再接收消息。<br>
     * <p>
     * 注意：调用close后，仍然可以通过调用start，重新启动消费。
     */
    void close();

    /**
     * Created by chenxiaobian on 16/9/8.
     */
    class ConsumerConfig {
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
}
