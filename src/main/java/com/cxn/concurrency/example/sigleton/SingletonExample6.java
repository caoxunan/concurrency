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
public class SingletonExample6 {

    // 私有化构造函数
    private SingletonExample6() {
    }

    // 单例对象,使用静态代码块时要注意代码的执行顺序，
    // 如果把下面这行代码放到静态代码块下面的话，对象初始化时候会变成null
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态的工厂方法
    public static SingletonExample6 getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonExample6();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                SingletonExample6 instance = SingletonExample6.getInstance();
                log.info("{}", instance);
            });

        }
    }


}
