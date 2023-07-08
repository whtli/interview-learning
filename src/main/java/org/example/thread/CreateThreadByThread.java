package org.example.thread;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description:
 */
public class CreateThreadByThread extends Thread{
    private int number;

    public CreateThreadByThread () {
        number = 10010;
    }

    public static void main(String[] args) {
        CreateThreadByThread thread1 = new CreateThreadByThread();
        thread1.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 使用Thread创建线程测试" + this.number);
    }
}
