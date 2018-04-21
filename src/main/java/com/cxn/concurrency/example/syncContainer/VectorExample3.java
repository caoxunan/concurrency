package com.cxn.concurrency.example.syncContainer;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @program: concurrency
 * @author: cxn
 * @create: 2018-04-21 21:15
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class VectorExample3 {

    // 在遍历的过程中不要做更新，在遍历的过程中做标记，然后遍历之后在做更新操作
    private static void  test1(Vector<Integer> vector){
        for (Integer integer : vector) {
            if (integer.equals(3)) {
                vector.remove(integer);

            }
        }
    }

    private static void  test2(Vector<Integer> vector){
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                vector.remove(i);

            }
        }
    }

    private static void  test3(Vector<Integer> vector){
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).equals(3)) {
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(5000);
        vector.add(1);
        vector.add(2);
        vector.add(3);

        //test1(vector);
        test2(vector);
        //test3(vector);

    }
}
