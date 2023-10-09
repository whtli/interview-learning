package org.example.ch01_java.ch04_concurrent.threadlocal;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: ThreadLocal在GC之后key是否为null测试代码
 * 问题：ThreadLocal的key是弱引用，那么在ThreadLocal.get()的时候，发生GC之后，key是否是null？
 */
public class ThreadLocalGCTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> test("001", false));
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----after GC----");
        Thread thread2 = new Thread(() -> test("002", true));
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test(String str, boolean isGC) {
        try {
            // new ThreadLocal<>().set(str);
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            threadLocal.set(str);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clazz = t.getClass();
            Field field = clazz.getDeclaredField("threadLocals");
            field.setAccessible(true);

            Object ThreadLocalMap = field.get(t);
            Class<?> tlmClass = ThreadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);

            Object[] arr = (Object[]) tableField.get(ThreadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceFiled = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceFiled.setAccessible(true);
                    System.out.println(String.format("弱引用 键：%s, 值：%s", referenceFiled.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
