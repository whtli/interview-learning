package org.example.ch01_java.ch04_concurrent.p07_atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicBoolean功能测试
 */
public class AtomicBooleanTest {
    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(true);
        System.out.println(flag.get());
        flag.compareAndSet(true, false);
        System.out.println(flag.get());
        flag.getAndSet(true);
        System.out.println(flag);
    }
}
