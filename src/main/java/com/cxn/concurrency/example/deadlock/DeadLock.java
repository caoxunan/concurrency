package com.cxn.concurrency.example.deadlock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 20:06
 * @Version v1.0
 */
@Slf4j
public class DeadLock implements  Runnable{

    // 首先定义公共的资源
    public int flag = 1;
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    @Override
    public void run() {

        if (flag == 1) {
            synchronized (o1){
                flag = 0;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    log.info("请求o2的资源。。");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info("请求o1的资源。。");
                }
            }
        }

    }


    public static void main(String[] args) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my-first-ThreadPoolExecutor").build();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), threadFactory);
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        threadPoolExecutor.execute(deadLock1);
        threadPoolExecutor.execute(deadLock2);

    }
}
