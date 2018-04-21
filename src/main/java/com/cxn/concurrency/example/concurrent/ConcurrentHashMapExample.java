package com.cxn.concurrency.example.concurrent;

import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 21:15
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        // create ThreadPool
        log.info("coming into the method~");
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);

                }
                countDownLatch.countDown();

            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("list.size():{}",map.size());

    }

    public static void update(int i){

        map.put(i, i);
    }
}
