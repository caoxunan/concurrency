package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.NotRecommend;
import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 懒汉模式，单例实例在第一次使用时进行创建,不推荐使用synchronized
 * @author: cxn
 * @create: 2018-04-21 17:53
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有化构造函数
    private SingletonExample3() {
    }

    // 单利对象
    private static SingletonExample3 instance = null;
    // 静态的工厂方法
    public static  synchronized  SingletonExample3 getInstance(){
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonExample3();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample3 instance = SingletonExample3.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
