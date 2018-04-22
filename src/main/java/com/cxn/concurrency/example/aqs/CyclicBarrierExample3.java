package com.cxn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 10:12
 * @Version v1.0
 */
@Slf4j
public class CyclicBarrierExample3 {

    // 定义需要同步等待的线程的数量
    private static  CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
        log.info("callback is ready~");
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                }
                catch (Exception e) {
                    log.error("exception",e);
                }
            });

        }
        executorService.shutdown();
    }

    private  static void race(int threadNum) throws  Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
            cyclicBarrier.await();
        log.info("{} continue~",threadNum);
    }

}
