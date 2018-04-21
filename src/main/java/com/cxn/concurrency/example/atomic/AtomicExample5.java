package com.cxn.concurrency.example.atomic;

import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-17 09:11
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    @Getter
    private volatile int count = 100;
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)){
            log.info("update success1,{}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)){
            log.info("update success2,{}", example5.getCount());
        }else {
            log.info("update failed,{}", example5.getCount());
        }

    }

}
