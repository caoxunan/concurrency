package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 懒汉模式，单例实例在第一次使用时进行创建
 * @author: cxn
 * @create: 2018-04-21 17:53
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class SingletonExample1 {

    // 私有化构造函数
    private SingletonExample1() {
    }

    // 单利对象
    private static SingletonExample1 instance = null;
    // 静态的工厂方法
    public static SingletonExample1 getInstance(){
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonExample1();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample1 instance = SingletonExample1.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
