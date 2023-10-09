package org.example.ch01_java.ch04_concurrent.ch01_thread;

/**
 * @author: whtli
 * @date: 2023/08/18
 * @description: 模拟线程死锁
 * 可以借助jconsole或jvisual等工具来查看当前死锁住的线程
 */
public class DeadLockDemo {
    private static Object resource1;
    private static Object resource2;

    public static void main(String[] args) {
        resource1 = new Object();
        resource2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread().getName() + " get resource2");
                    }
                }
            }
        }, "线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " waiting get resource1");
                    synchronized (resource1) {
                        System.out.println(Thread.currentThread().getName() + " get resource1");
                    }
                }
            }
        }, "线程2").start();

        /**
         * 将线程2修改如下可避免死锁
         */
        /*new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 2").start();*/
    }
}
