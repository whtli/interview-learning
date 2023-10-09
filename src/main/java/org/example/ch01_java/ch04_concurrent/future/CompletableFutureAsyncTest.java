package org.example.ch01_java.ch04_concurrent.future;

import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: CompletableFuture相关，线程阻塞问题
 */
public class CompletableFutureAsyncTest {
    public static void main(String[] args) {
        // 线程阻塞问题
        block();

        // 线程池循环引用会导致死锁
        doGet();
    }

    /**
     * 要合理治理线程资源，最基本的前提条件就是要在写代码时，清楚地知道每一行代码都将执行在哪个线程上
     * https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
     */
    public static void block() {
        ExecutorService threadPool = new ThreadPoolExecutor(10
                , 10
                , 0L
                , TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue<>(100));
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            // 业务操作……
            return "";
        }, threadPool);
        // 此时，如果future中的业务操作已经执行完毕并返回
        // 则该thenApply直接由当前main线程执行
        // 否则将会由执行以上业务操作的threadPool中的线程执行
        future.thenApply(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });
        // 不携带Executor参数，则使用ForkJoinPool中的共用线程池CommonPool
        future.thenApplyAsync(value -> {
            System.out.println("thenApplyAsync 01 执行线程：" + Thread.currentThread().getName());
            // 业务操作……
            return value + "1";
        });
        // 使用指定线程池
        future.thenApplyAsync(value -> {
            System.out.println("thenApplyAsync 02 执行线程：" + Thread.currentThread().getName());
            // 业务操作……
            return value + "1";
        }, threadPool);

        threadPool.shutdown();
        System.out.println();
    }

    /**
     * 线程池循环引用会导致死锁
     * 异步回调方法可以选择是否传递线程池参数Executor，可以强制传线程池，且根据实际情况做线程池隔离
     */
    public static Object doGet() {
        ExecutorService threadPool = new ThreadPoolExecutor(1
                , 2
                , 0L
                , TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue<>(100));
        // 通过supplyAsync向threadPool请求线程，并且内部子任务又向threadPool请求线程
        // threadPool核心线程数为1，当同一时刻有1个请求到达，则threadPool被打满，子任务请求线程时进入阻塞队列排队
        // 但是父任务的完成又依赖于子任务，这时由于子任务得不到线程，父任务无法完成
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            // 业务操作……
            return CompletableFuture.supplyAsync(() -> {
                // 子任务
                System.out.println("child");
                return "child";
            }, threadPool).join();
        }, threadPool);
        // 主线程执行cf1.join()进入阻塞状态，并且永远无法恢复
        // 为了修复该问题，需要将父任务与子任务做线程池隔离，两个任务请求不同的线程池，避免循环依赖导致的阻塞
        Object res = cf1.join();
        threadPool.shutdown();
        return res;
    }
}
