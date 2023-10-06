package org.example.ch01_java.ch04_concurrent.thread;

import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/08/26
 * @description: 获取指定时间内完成任务的线程,并将返回结果组合
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务1，睡眠1秒
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务2，睡眠2秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务3，睡眠3秒
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        CompletableFuture<Object> allOf = CompletableFuture.anyOf(task1, task2, task3);

        try {
            allOf.get(4, TimeUnit.SECONDS); // 等待所有任务完成
            Thread.sleep(2000);
            System.out.println(task1.get(1, TimeUnit.SECONDS));
            System.out.println(task2.get(1, TimeUnit.SECONDS));
            System.out.println(task3.get(1, TimeUnit.SECONDS));
//            if (task1.isDone()) {
//                System.out.println("Task 1 Result: " + task1.get());
//                System.out.println(task1.get());
//            }
//            if (task2.isDone()) {
//                System.out.println("Task 2 Result: " + task2.get());
//                System.out.println(task2.get());
//            }
//            if (task3.isDone()) {
//                System.out.println("Task 3 Result: " + task3.get());
//                System.out.println(task1.get());
//            }
        } catch (Exception e) {
            System.out.println("任务在时间限制内未完成。");
            // 在这里你可以处理任务未在时间限制内完成的情况
        }
    }
}
