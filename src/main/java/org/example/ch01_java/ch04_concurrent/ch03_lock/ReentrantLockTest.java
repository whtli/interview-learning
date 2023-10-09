package org.example.ch01_java.ch04_concurrent.ch03_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: whtli
 * @date: 2023/04/02
 * @description:
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Lock[] locks = new ReentrantLock[5];
        Condition condition = locks[0].newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        condition.signalAll();
    }
}