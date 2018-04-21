package com.cxn.concurrency.example.commonUnsafe;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 21:09
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

    private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyyMMdd");

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
                    add1();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);

                }
                countDownLatch.countDown();

            });
        }

        countDownLatch.await();
        executorService.shutdown();

    }

    public static void add1(){

        try {
            simpleDateFormat.parse("20180421");
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("exception:",e);
        }
    }


}
