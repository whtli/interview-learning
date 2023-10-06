package org.example.ch01_java.ch04_concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: whtli
 * @date: 2023/04/02
 * @description:
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock();
        lock.writeLock();
    }
}
