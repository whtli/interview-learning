package org.example.ch01_java.ch04_concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: whtli
 * @date: 2023/10/07
 * @description: 手动实现ThreadFactory，给线程池中的线程命名
 */
public class NamingThreadFactoryTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new NamingThreadFactory("my-thread-pool-");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(50),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}

class NamingThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(0);
    private final String name;

    /**
     * 构造函数
     * 创建一个带名字的线程池生产工厂
     */
    public NamingThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name + "[" + threadNumber.getAndIncrement() + "]");
        return thread;
    }
}

