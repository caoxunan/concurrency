package com.cxn.concurrency.example.threadpool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-26 15:30
 * @Version v1.0
 */
public class ThreadPoolExectorDemo {

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-name-feature").build();

    /**
     * 采用LinkedBlockingQueue,最多可有50000个任务在阻塞队列中，
     * 线程池最大线程数40个，核心池大小设置为2个，多出来的38个线程最多活跃60秒就会被回收
     * 拒绝策略采用CallerRunsPolicy，不会丢弃任务，只要线程池未关闭，该策略直接在调用者线程中运行当前被丢弃的任务
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(
                    2,
                    40,
                    60L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(50000),
                    threadFactory ,
                    new ThreadPoolExecutor.CallerRunsPolicy());


}
