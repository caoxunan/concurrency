package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 双重同步锁
 * @author: cxn
 * @create: 2018-04-21 17:53
 * @Version v1.0
 */
@Slf4j
@ThreadSafe
public class SingletonExample5 {

    // 私有化构造函数
    private SingletonExample5() {
    }
    // 正常指令执行顺序，但是由于JVM和CPU指令重排的存在 2和3的顺序可能发生变化，也不是完全安全的，容易出现对象逸出的可能
    // 1、memory = allocate（）分配对象的内存空间
    // 2、ctorInstance（）初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // 使用volatile关键字可以防止出现cpu指令重排，可以实现安全的单例模式
    private static volatile SingletonExample5 instance = null;
    // 静态的工厂方法
    public static SingletonExample5 getInstance(){
        if (instance == null) {     // ThreadB-
            synchronized (SingletonExample5.class){
                if (instance == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample5 instance = SingletonExample5.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
