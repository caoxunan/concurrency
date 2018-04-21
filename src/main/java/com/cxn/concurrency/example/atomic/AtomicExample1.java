package com.cxn.concurrency.example.atomic;

import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-17 09:11
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {

    private static AtomicInteger count = new AtomicInteger(0);
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    public static void main(String[] args) throws Exception {
        // create ThreadPool
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
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
        log.info("count:{}",count.get());

    }

    public static void add(){
        // 先获取值然后加一 or 先加一在获取值
        count.getAndIncrement();
        // count.incrementAndGet();
    }

}
