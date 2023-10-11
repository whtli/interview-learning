package org.example.ch01_java.ch04_concurrent.p02_volatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.*;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description: volatile关键字
 * 能保证变量的可见性，但不能保证对变量的操作是原子性的
 */
public class VolatileAtomicityDemo {
}

class Demo1 {
    public volatile static int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = newFixedThreadPool(5);
        Demo1 demo = new Demo1();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    demo.increase();
                }
            });
        }
        Thread.sleep(1500);
        // 输出结果小于2500
        System.out.println(inc);
        threadPool.shutdown();
    }
}

class Demo2 {
    public volatile static int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = newFixedThreadPool(5);
        Demo2 demo = new Demo2();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    demo.increase();
                }
            });
        }
        Thread.sleep(1500);
        // 输出结果等于2500
        System.out.println(inc);
        threadPool.shutdown();
    }
}


class Demo3 {
    public static AtomicInteger inc = new AtomicInteger(0);

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = newFixedThreadPool(5);
        Demo3 demo = new Demo3();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    demo.increase();
                }
            });
        }
        Thread.sleep(1500);
        // 输出结果等于2500
        System.out.println(inc.get());
        threadPool.shutdown();
    }
}


class Demo4 {
    Lock lock = new ReentrantLock();

    public static int inc = 0;

    public void increase() {
        lock.lock();
        try {
            ++inc;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = newFixedThreadPool(5);
        Demo4 demo = new Demo4();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    demo.increase();
                }
            });
        }
        Thread.sleep(1500);
        // 输出结果等于2500
        System.out.println(inc);
        threadPool.shutdown();
    }
}