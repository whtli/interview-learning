package org.example.ch01_java.ch04_concurrent.relate_problems;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderPrint {
    public static void main(String[] args) {
        // 三个线程按顺序打印1~100
        for (int i = 1; i <= 3; i++) {
            new MyThread("thread-" + i, i, (i % 3) + 1);
        }
    }
}

class MyThread extends Thread {
    private static final Object resource = new Object();
    // 打印区分标识(值为1~3，初始设置1)
    private static int flag = 1;
    // 需要打印的值
    private static AtomicInteger integer = new AtomicInteger(1);

    public MyThread(String name, int ff, int next) {
        new Thread(() -> {
            while (integer.get() <= 100) {
                synchronized (resource) {
                    while (flag != ff) {
                        try {
                            resource.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (integer.get() <= 100) {
                        System.out.println(currentThread().getName() + " : ----------- " + integer.getAndIncrement());
                    } else {
                        currentThread().interrupt();
                    }
                    flag = next;
                    resource.notifyAll();
                }
            }
        }, name).start();
    }
}