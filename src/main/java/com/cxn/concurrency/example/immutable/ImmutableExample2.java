package com.cxn.concurrency.example.immutable;

import com.cxn.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 19:36
 * @Version v1.0
 */
@ThreadSafe
public class ImmutableExample2 {

    private static final Integer a = 1;
    private static final String b = "2";
    private static  Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,4);
        System.out.println(map);
    }


}
