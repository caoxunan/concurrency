package com.cxn.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 11:53
 * @Version v1.0
 */
@Slf4j
public class ConditionExample {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        Condition condition = reentrantLock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    log.info("wait signal");
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("get signal");
                reentrantLock.unlock();

            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10);
                reentrantLock.lock();
                log.info("get lock");
                for (int i = 0; i < 3; i++) {
                    log.info("{} 秒后开始发送信号",3-i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send sigal ~");
            reentrantLock.unlock();
        }).start();
    }

}
