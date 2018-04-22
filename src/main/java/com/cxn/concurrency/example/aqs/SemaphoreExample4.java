package com.cxn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 09:36
 * @Version v1.0
 */
@Slf4j
public class SemaphoreExample4 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(20);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    // 尝试获取n个许可,规定时间内没有获得许可的话就放弃动作 return false
                    if (semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        semaphore.release();
                    }

                } catch (InterruptedException e) {
                    log.error("exception",e);
                }finally {
                    // 根据实际需要决定什么时候countDown()
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finish~");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}-",threadNum);
        Thread.sleep(1000);
    }

}
