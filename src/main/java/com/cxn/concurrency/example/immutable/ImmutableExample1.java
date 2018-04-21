package com.cxn.concurrency.example.immutable;

import com.google.common.collect.Maps;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 19:36
 * @Version v1.0
 */
@NotThreadSafe
public class ImmutableExample1 {

    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        // a = 2;
        // b = "sdd";
        // map = new HashMap<>();
        map.put(1,4);
        System.out.println(map);
    }

    private void test (final int a ){
        // a = 4;
    }

}
