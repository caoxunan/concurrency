package com.cxn.concurrency.example.sigleton;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrency
 * @description: 双重同步锁（不限制指令重排序的话，是线程不安全的，虽然打印的对象地址一样，但是有对象逸出的可能）
 * @author: cxn
 * @create: 2018-04-21 17:53
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class SingletonExample4 {

    // 私有化构造函数
    private SingletonExample4() {
    }
    // 正常指令执行顺序，但是由于JVM和CPU指令重排的存在 2和3的顺序可能发生变化，也不是完全安全的，容易出现对象逸出的可能
    // 1、memory = allocate（）分配对象的内存空间
    // 2、ctorInstance（）初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // 单利对象
    private static SingletonExample4 instance = null;
    // 静态的工厂方法
    public static SingletonExample4 getInstance(){
        if (instance == null) {     // ThreadB-
            synchronized (SingletonExample4.class){
                if (instance == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonExample4();// ThreadA-由于指令重排，先执行3分配了内存，但是还没有执行2进行对象初始化

                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample4 instance = SingletonExample4.getInstance();
                log.info("{}",instance);
            });

        }
    }


}
