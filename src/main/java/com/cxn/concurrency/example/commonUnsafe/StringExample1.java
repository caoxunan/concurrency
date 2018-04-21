package com.cxn.concurrency.example.commonUnsafe;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 21:00
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class StringExample1 {

    private static StringBuilder stringBuilder = new StringBuilder();
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    public static void main(String[] args) throws Exception {
        // create ThreadPool
        log.info("coming into the method~");
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);

                }
                countDownLatch.countDown();

            });
        }

        countDownLatch.await();
        executorService.shutdown();
        // 当所有线程执行完成之后打印count的值
        log.info("length:{}",stringBuilder.length());

    }

    public static void update(){
        stringBuilder.append("1");

    }

}
