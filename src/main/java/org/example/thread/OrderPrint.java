package org.example.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderPrint {
    // https://blog.csdn.net/weixin_42950079/article/details/124476567
    // 锁
    private static Object resource = new Object();
    // 打印区分标识(值为1~3，初始设置1)
    private static int flag = 1;
    // 需要打印的值
    static AtomicInteger integer = new AtomicInteger(1);
    // private static volatile int number = 1;

    public static void main(String[] args) {
        // 三个线程按顺序打印1~100
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (integer.get() <= 100) {
                    synchronized (resource) {
                        while (flag != 1) {
                            try {
                                resource.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // 打印操作
                        if (integer.get() <= 100) {
                            System.out.println(Thread.currentThread().getName() + " --- " + integer.getAndIncrement());
                        } else {
                            Thread.currentThread().interrupt();
                            // break;
                        }
                        // number ++;
                        // 变更标识
                        flag = 2;
                        // 通知线程2
                        resource.notifyAll();
                    }
                }
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (integer.get() <= 100) {
                    synchronized (resource) {
                        while (flag != 2) {
                            try {
                                resource.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // 打印操作
                        if (integer.get() <= 100) {
                            System.out.println(Thread.currentThread().getName() + " --- " + integer.getAndIncrement());
                        } else {
                            Thread.currentThread().interrupt();
                            // break;
                        }
                        // 变更标识
                        flag = 3;
                        // 通知线程3
                        resource.notifyAll();
                    }
                }
            }
        }, "线程2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (integer.get() <= 100) {
                    synchronized (resource) {
                        while (flag != 3) {
                            try {
                                resource.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // 打印操作
                        if (integer.get() <= 100) {
                            System.out.println(Thread.currentThread().getName() + " --- " + integer.getAndIncrement());
                        } else {
                            Thread.currentThread().interrupt();
                            // break;
                        }
                        // 变更标识
                        flag = 1;
                        // 通知线程1
                        resource.notifyAll();
                    }
                }
            }
        }, "线程3").start();
    }
}
