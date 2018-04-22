package com.cxn.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 19:30
 * @Version v1.0
 */
@Slf4j
public class ExecutorsNewScheduleExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //for (int i = 0; i < 10; i++) {
        //    final int index = i;
        //    scheduledExecutorService.scheduleAtFixedRate(()->{
        //        log.info("task:{}", index);
        //    },1,3,TimeUnit.SECONDS);
        //}
       // scheduledExecutorService.shutdown();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5*1000);

    }

}
