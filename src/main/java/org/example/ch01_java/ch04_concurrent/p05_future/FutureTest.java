package org.example.ch01_java.ch04_concurrent.p05_future;

import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/10/07
 * @description: Future功能测试
 */
public class FutureTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                10000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(50),
                new ThreadPoolExecutor.AbortPolicy()
        );
        Future<Integer> task = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 54;
            }
        });

        // 在某个时刻获取任务的结果
        try {
            System.out.println("等待计算结果...");
            // 调用后阻塞，直到计算完成
            Integer result = task.get();
            System.out.println("计算结果是: " + result);
        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("计算出错: " + e.getCause());
        }
        // 关闭线程池
        executor.shutdown();
    }
}
