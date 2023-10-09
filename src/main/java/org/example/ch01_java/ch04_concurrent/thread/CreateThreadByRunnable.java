package org.example.ch01_java.ch04_concurrent.thread;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description: 实现Runnable接口创建线程
 */
public class CreateThreadByRunnable {
}

class CreateThreadByRunnable1 implements Runnable {
    public static void main(String[] args) {
        System.out.println("主线程名称：" + Thread.currentThread().getName());
        Thread thread1 = new Thread(new CreateThreadByRunnable1());
        thread1.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：实现Runnable接口创建线程1");
    }
}

class CreateThreadByRunnable2 {
    public static void main(String[] args) {
        System.out.println("主线程名称：" + Thread.currentThread().getName());
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：实现Runnable接口创建线程2");
            }
        });
        thread2.start();
    }
}

class CreateThreadByRunnable3 {
    public static void main(String[] args) {
        Thread thread3 = new Thread(()
                -> System.out.println(Thread.currentThread().getName() + "：实现Runnable接口（借助lambda表达式）创建线程3"));
        thread3.start();
    }
}