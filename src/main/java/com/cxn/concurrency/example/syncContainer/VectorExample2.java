package com.cxn.concurrency.example.syncContainer;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

/**
 * @program: concurrency
 * @description: 同步容器 并不是所有的场合下都能做到线程安全，例如本例：一个线程在get操作，另一个线程remove操作，就会造成下标越界异常
 * @author: cxn
 * @create: 2018-04-21 21:15
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {


    private static List<Integer> vector = new Vector<>(5000);

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }


            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
