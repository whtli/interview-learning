package org.example.java.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: whtli
 * @date: 2023/04/08
 * @description:
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        System.out.println(atomicInteger1.incrementAndGet());
    }
}
