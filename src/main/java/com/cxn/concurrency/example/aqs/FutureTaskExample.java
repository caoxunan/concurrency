package com.cxn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 14:15
 * @Version v1.0
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable~");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    log.info("sleep倒计时：{}", 5-i);
                }
                return "Done";
            }
        });
        // Thread 调用
        // new Thread(futureTask).start();

        // 线程池调用
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        // executorService.execute(futureTask);
        executorService.shutdown();

        log.info("do something in main~");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}", result);


    }


}
