package org.example.ch01_java.ch04_concurrent.thread.pool;

import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description:
 */
public class CreatePoolByThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadPoolExecutor使用测试");
            }
        });
        // Create thread pool by Executors
        ExecutorService service1 = Executors.newFixedThreadPool(1);
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();
        ExecutorService service4 = Executors.newScheduledThreadPool(3);
    }
}
