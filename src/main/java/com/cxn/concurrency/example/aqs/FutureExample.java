package com.cxn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 12:37
 * @Version v1.0
 */
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable~");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                log.info("sleep倒计时：{}", 5-i);
            }
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(new MyCallable());
        log.info("do somthing in main");
        Thread.sleep(1000);
        String s = future.get();
        log.info("result:{}", s);

    }

}
