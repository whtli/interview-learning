package org.example.ch01_java.ch04_concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description: ThreadLocal
 */
public class ThreadLocalExample implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MMdd-HHmm"));

    @Override
    public void run() {
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // formatter的样式在此处被当前线程更改，但这个变更不会影响到其他线程
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
    }

    public static void main(String[] args) {
        ThreadLocalExample obj = new ThreadLocalExample();
        // 观察输出结果可以得知，虽然Thread-i已经改变了formatter的值，但Thread-j默认格式化值与初始化值相同
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(obj, "" + i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t.start();
        }
    }
}
