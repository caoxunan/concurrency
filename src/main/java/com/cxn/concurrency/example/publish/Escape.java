package com.cxn.concurrency.example.publish;

import com.cxn.concurrency.annotations.NotRecommend;
import com.cxn.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: concurrency
 * @description: 原则：在对象构造完成之前，不允许使被其他线程所见（本例是反例）
 * @author: cxn
 * @create: 2018-04-21 17:40
 * @Version v1.0
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }
    public Escape(int thisCanBeEscape) {
        this.thisCanBeEscape = thisCanBeEscape;
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
