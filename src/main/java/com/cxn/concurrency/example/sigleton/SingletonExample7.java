package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.Recommend;
import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 通过枚举实现单例模式,最安全的
 * @author: cxn
 * @create: 2018-04-21 19:20
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample7 {
    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;
        // JVM 保证这个方法只会调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getSingleton() {
            return singleton;
        }

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample7 instance = SingletonExample7.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
