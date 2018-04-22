package com.cxn.concurrency.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: concurrency
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-22 11:29
 * @Version v1.0
 */
public class StampLockExample {

    class Point{
        private double x,y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY){
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            }finally {
                sl.unlockWrite(stamp);
            }
        }
        // 下面看乐观读锁案例
        // A read-only method
        double distanceFromOrigin(){
            // 获得一个乐观读锁
            long stamp = sl.tryOptimisticRead();
            // 将2个字段读如本地局部变量
            double currentX = x, currentY = y;
            // 检查发出乐观读锁后同时是否有其它写锁发生
            if (!sl.validate(stamp)) {
                // 如果没有,再次获得一个读悲观锁
                stamp = sl.readLock();
                try {
                    // 将2个字段读入本地变量
                    currentX  = x;
                    currentY = y;
                }finally {
                    sl.unlock(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        // 悲观锁读取的案例
        void moveIfAtOrigin(double newX, double newY){
            long stamp = sl.readLock();
            try {
                // 循环检查当前状态是否符合
                while (x == 0.0 && y == 0.0) {
                    // 将读锁转为写锁
                    long ws = sl.tryConvertToWriteLock(stamp);
                    // 这是确认转为写锁是否成功
                    if (ws !=0L) {
                        // 如果成功，替换数据
                        stamp = ws;
                        // 进行状态改变
                        x = newX;
                        y = newY;
                        break;
                    }else{
                        // 如果不能成功转换为写锁，就显示释放读锁
                        sl.unlockRead(stamp);
                        stamp = sl.writeLock();
                    }
                }
            }finally {
                // 释放读锁或写锁
                sl.unlock(stamp);
            }

        }

    }

}
