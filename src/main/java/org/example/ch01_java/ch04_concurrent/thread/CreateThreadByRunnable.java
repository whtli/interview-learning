package org.example.ch01_java.ch04_concurrent.thread;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description:
 */
public class CreateThreadByRunnable implements Runnable{
    public static void main(String[] args) {
        Thread thread2 = new Thread(new CreateThreadByRunnable());
        thread2.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 使用Runnable创建线程测试1");
    }
}

//public class CreateThreadByRunnable {
//    public static void main(String[] args) {
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + " 使用Runnable创建线程测试2");
//            }
//        });
//        thread2.start();
//    }
//}

//public class CreateThreadByRunnable{
//    public static void main(String[] args) {
//        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " 使用Runnable创建线程测试3"));
//        thread2.start();
//    }
//}