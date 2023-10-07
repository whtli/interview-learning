package org.example.ch01_java.ch04_concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/08/26
 * @description: 获取指定时间内完成任务的线程，并将返回结果组合
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        List<Object> result = getResult();
        System.out.println(result);
    }

    public static List<Object> getResult() {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务1，睡眠0.5秒
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务2，睡眠3秒
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2";
        });

        CompletableFuture<Boolean> task3 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务3，睡眠6秒
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });

        List<Object> ans = new ArrayList<>();
        try {
            Thread.sleep(4000);
            CompletableFuture<Void> res = CompletableFuture.allOf(task1, task2, task3);
            // CompletableFuture<Object> res = CompletableFuture.anyOf(task1, task2, task3);
            System.out.println(res);
            if (task1.isDone()) {
                System.out.println("Task 1 Result: " + task1.get());
                ans.add(task1.get());
            } else {
                System.out.println("Task 1 is not done.");
            }
            if (task2.isDone()) {
                System.out.println("Task 2 Result: " + task2.get());
                ans.add(task2.get());
            } else {
                System.out.println("Task 2 is not done.");
            }
            if (task3.isDone()) {
                System.out.println("Task 3 Result: " + task3.get());
                ans.add(task3.get());
            } else {
                System.out.println("Task 3 is not done.");
            }
        } catch (Exception e) {
            // 此处可以处理任务未在时间限制内完成的情况
            System.out.println("任务在时间限制内未完成。");
        }
        return ans;
    }
}