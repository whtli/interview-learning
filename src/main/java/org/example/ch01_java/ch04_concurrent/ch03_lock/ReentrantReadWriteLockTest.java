package org.example.ch01_java.ch04_concurrent.ch03_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: whtli
 * @date: 2023/04/02
 * @description:
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        // 默认非公平
        ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();
        lock1.readLock();
        lock1.writeLock();

        // 指定公平
        ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock(true);
        lock2.readLock();
        lock2.writeLock();
    }
}
