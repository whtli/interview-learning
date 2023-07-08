package org.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: whtli
 * @date: 2023/03/31
 * @description:
 */
public class CreateThreadByCallable implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CreateThreadByCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String ans = futureTask.get();
        System.out.println(thread.getName() + ans);
    }

    @Override
    public String call() throws Exception {
        return " 使用Callable创建线程";
    }
}
