package org.example.ch01_java.ch04_concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: whtli
 * @date: 2023/10/07
 * @description: LongAddr并发测试
 */
public class LongAddrTest {
    public static void main(String[] args) {
        // 空间换时间，LongAdder在高并发场景下比AtomicInteger和AtomicLong的性能更好
        LongAdder sum = new LongAdder();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                50,
                100,
                10000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(50),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    sum.increment();
                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sum);
        executor.shutdown();
    }
}
