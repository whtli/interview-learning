package org.example.ch01_java.ch04_concurrent.p05_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/08/26
 * @description: CompletableFuture相关
 * 获取指定时间内完成任务的线程，并将返回结果组合
 * 参考：https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        baseAbility();

        int timeout = 4000;
        List<Object> result = getResult(timeout);
        System.out.println("共有以下任务顺利完成：" + result);
    }

    private static void baseAbility() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("hello0!").thenApply(s -> s + " world0!");
        System.out.println(future.get());
        // 这个调用将被忽略
        future.thenApply(s -> s + " nice0!");
        System.out.println(future.get());

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("hello1!")
                .thenApply(s -> s + " world1!").thenApply(s -> s + " nice1!");
        System.out.println(future1.get());

        CompletableFuture.completedFuture("hello1!")
                .thenApply(s -> s + " world1!").thenApply(s -> s + " nice1!").thenAccept(System.out::println);

        CompletableFuture.completedFuture("hello1!")
                .thenApply(s -> s + " world1!").thenApply(s -> s + " nice1!").thenRun(() -> System.out.println("hello1!"));

        // whenComplete可以处理两个参数
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello2 world2!")
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // System.out.println(ex.getMessage());
                });
        System.out.println(future2.get());

        // 可以通过 handle() 方法来处理任务执行过程中可能出现的抛出异常的情况
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error 3!");
            }
            return "hello3!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : "world3!";
        });
        System.out.println(future3.get());

        // 还可以通过 exceptionally() 方法来处理异常情况
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error 4!");
            }
            return "hello4!";
        }).exceptionally(ex -> {
            // CompletionException
            System.out.println(ex.toString());
            return "world4!";
        });
        System.out.println(future4.get());

        // 可以通过completeExceptionally()直接设置异常
        CompletableFuture<String> future5 = new CompletableFuture<>();
        future5.completeExceptionally(new RuntimeException("Calculation failed 5!"));
        // System.out.println(completableFuture.get()); // ExecutionException

        // thenCompose()可以链接两个CompletableFuture对象，并将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "hello6!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world6!"));
        System.out.println(future6.get());

        // thenCombine()会在两个任务都执行完成后，把两个任务的结果合并，两个任务是并行执行的，没有先后依赖顺序
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(
                        () -> "hello7!")
                .thenCombine(CompletableFuture.supplyAsync(() -> "world7!"), (s1, s2) -> s1 + s2);
        System.out.println(future7.get());
        System.out.println();

        // 异步: https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
        // 零依赖
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "result1";
        });
        System.out.println(cf1.get());
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "result2";
        });
        System.out.println(cf2.get());
        // 一元依赖
        CompletableFuture<String> cf3 = cf1.thenApply(result1 -> {
            //result1为CF1的结果
            System.out.println("执行step 2");
            return result1 + "result3";
        });
        System.out.println(cf3.get());
        CompletableFuture<String> cf5 = cf2.thenApply(result2 -> {
            //result2为CF2的结果
            System.out.println("执行step 2");
            return result2 + "result5";
        });
        System.out.println(cf5.get());
        // 二元依赖
        CompletableFuture<String> cf4 = cf1.thenCombine(cf2, (result1, result2) -> {
            // result1和result2分别为cf1和cf2的结果
            System.out.println("执行step 2");
            return result1 + result2 + "result4";
        });
        System.out.println(cf5.get());
        // 多元依赖
        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        CompletableFuture<String> result = cf6.thenApply(v -> {
            System.out.println("执行step 3");
            // 这里的join并不会阻塞，因为传给thenApply的函数是在CF3、CF4、CF5全部完成时，才会执行
            String result3 = cf3.join();
            String result4 = cf4.join();
            String result5 = cf5.join();
            //根据result3、result4、result5组装最终result;
            return result3 + result4 + result5;
        });
        System.out.println(result.get(0, TimeUnit.SECONDS));
        System.out.println();
    }

    public static List<Object> getResult(int timeout) {
        System.out.println("---- 需要异步操作且关心返回结果的时候，可以使用supplyAsync()方法，runAsync()返回值为空 ----");
        // 需要异步操作且关心返回结果的时候，可以使用supplyAsync()方法，runAsync()返回值为空
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
            Thread.sleep(timeout);
            CompletableFuture<Void> res = CompletableFuture.allOf(task1, task2, task3);
            // CompletableFuture<Object> res = CompletableFuture.anyOf(task1, task2, task3);
            System.out.println(res);
            // CompletableFuture的get()方法是阻塞的，尽量避免使用
            if (task1.isDone()) {
                // 获取异步任务的返回值，设置超时时间为0秒
                System.out.println("Task 1 Result: " + task1.get(0, TimeUnit.SECONDS));
                ans.add(task1.get());
            } else {
                System.out.println("Task 1 is not done.");
            }
            if (task2.isDone()) {
                System.out.println("Task 2 Result: " + task2.get(0, TimeUnit.SECONDS));
                ans.add(task2.get());
            } else {
                System.out.println("Task 2 is not done.");
            }
            if (task3.isDone()) {
                System.out.println("Task 3 Result: " + task3.get(0, TimeUnit.SECONDS));
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