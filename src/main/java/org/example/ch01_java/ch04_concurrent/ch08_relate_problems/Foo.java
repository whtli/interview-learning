package org.example.ch01_java.ch04_concurrent.ch08_relate_problems;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description: LeetCode1114.按序打印
 * 借助AtomicInteger、Semaphore、CountDownLatch实现
 * https://leetcode.cn/problems/print-in-order/description/
 */
public class Foo {

}

class Foo1 {
    private AtomicInteger flag12, flag23;

    public Foo1() {
        flag12 = new AtomicInteger(0);
        flag23 = new AtomicInteger(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag12.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (flag12.get() != 1) {
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag23.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (flag23.get() != 1) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo2 {
    private Semaphore flag12, flag23;

    public Foo2() {
        flag12 = new Semaphore(0);
        flag23 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag12.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        flag12.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag23.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        flag23.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo3 {
    private CountDownLatch flag12, flag23;

    public Foo3() {
        flag12 = new CountDownLatch(1);
        flag23 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag12.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        flag12.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag23.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        flag23.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
