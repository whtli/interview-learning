package org.example.ch01_java.ch04_concurrent.ch06_threadpool;

import java.util.Date;

/**
 * @author: whtli
 * @date: 2023/03/06
 * @description: 创建一个Runnable接口的实现类
 */
public class MyRunnable implements Runnable {
    private String command;

    public MyRunnable(String s) {
        command = s;
    }


    @Override
    public void run() {
        System.out.println("current thread: " + Thread.currentThread().getName() + " ; current time: " + new Date());
        processCommand();
        System.out.println("current thread: " + Thread.currentThread().getName() + " ; current time: " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
