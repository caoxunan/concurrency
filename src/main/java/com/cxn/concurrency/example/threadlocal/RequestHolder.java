package com.cxn.concurrency.example.threadlocal;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 20:30
 * @Version v1.0
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new InheritableThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    // 防止内存泄漏，否则永远不会被释放
    public static void remove(){
        requestHolder.remove();
    }

}
