package org.example.ch01_java.ch04_concurrent.ch07_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: whtli
 * @date: 2023/04/08
 * @description: AtomicInteger功能测试
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        int value = 0;
        AtomicInteger i = new AtomicInteger(0);
        value = i.getAndSet(3);
        System.out.println("value: " + value + ", i: " + i.get());
        value = i.getAndIncrement();
        System.out.println("value: " + value + ", i: " + i.get());
        value = i.getAndDecrement();
        System.out.println("value: " + value + ", i: " + i.get());
        value = i.addAndGet(5);
        System.out.println("value: " + value + ", i: " + i.get());
        boolean ans = i.compareAndSet(8, 11);
        System.out.println("ans: " + ans + ", i: " + i.get());
    }
}
