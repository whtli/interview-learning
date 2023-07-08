package org.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.*;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description: volatile 关键字能保证变量的可⻅性，但不能保证对变量的操作是原子性的
 */
public class VolatoleAtomicityDemo {
    // public AtomicInteger inc = new AtomicInteger();
    // public void increase() {
    //     inc.getAndIncrement();
    // }

//    public volatile static int inc = 0;
//    Lock lock = new ReentrantLock();
//    public void increase() {
//        lock.lock();
//        try {
//            inc++;
//        } finally {
//            lock.unlock();
//        }
//    }

    public volatile static int inc = 0;
    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = newFixedThreadPool(5);
        VolatoleAtomicityDemo volatoleAtomicityDemo = new VolatoleAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    volatoleAtomicityDemo.increase();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println(inc);
        threadPool.shutdown();
    }
}
