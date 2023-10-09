package org.example.ch01_java.ch04_concurrent.thread;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description: 通过继承Thread类创建线程
 */
public class CreateThreadByThread {
    public static void main(String[] args) {
        System.out.println("主线程名称：" + Thread.currentThread().getName());
        Thread thread1 = new MyThread();
        thread1.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName() + "：继承Thread类创建线程测试");
    }
}