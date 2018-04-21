package com.cxn.concurrency.example.immutable;

import com.cxn.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 20:00
 * @Version v1.0
 */
@ThreadSafe
public class ImmutableExample3 {

    // 声明guava集合包的对象
    private final static ImmutableList list = ImmutableList.of(1,2,3,4,5);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Object, Object> map2 = ImmutableMap.builder().put(1,2).put(3,4).build();

    public static void main(String[] args) {
        //list.add(4);
        //set.add(5);
        //map.put(1,5);
        //map2.put("5","4");
        System.out.println(map.get(3));
    }
}
