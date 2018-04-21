package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 饿汉模式，单例实例在类装载的时候创建
 * @author: cxn
 * @create: 2018-04-21 17:53
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
public class SingletonExample2 {

    // 私有化构造函数
    private SingletonExample2() {
    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();
    // 静态的工厂方法
    public static SingletonExample2 getInstance(){
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonExample2();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample2 instance = SingletonExample2.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
