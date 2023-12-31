package org.example.ch01_java.ch04_concurrent.p01_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description: 使用ExecutorService创建线程
 */
public class CreateThreadByExecutor implements Runnable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            executorService.execute(new CreateThreadByExecutor());
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：使用ExecutorService创建线程");
    }
}
