package com.cxn.concurrency.example.publish;

import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-21 17:37
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a","b","c"};
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));

    }
}
