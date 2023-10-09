package org.example.ch01_java.ch04_concurrent.ch01_thread;

import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description: 实现Callable接口创建线程
 */
public class CreateThreadByCallable implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("主线程名称：" + Thread.currentThread().getName());

        FutureTask<String> futureTask = new FutureTask<>(new CreateThreadByCallable());
        Thread thread = new Thread(futureTask);
        thread.start();

        String ans = futureTask.get(0, TimeUnit.SECONDS);
        System.out.println(thread.getName() + ans);
    }

    @Override
    public String call() {
        return ": 实现Callable接口创建线程";
    }
}
