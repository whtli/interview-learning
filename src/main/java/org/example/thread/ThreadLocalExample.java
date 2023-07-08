package org.example.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description:
 */
public class ThreadLocalExample implements Runnable {
    public static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
    }
}
