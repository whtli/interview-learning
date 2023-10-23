package org.example.ch01_java.ch04_concurrent.p06_threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: whtli
 * @date: 2023/10/23
 * @description: 获取第一个执行完成的线程
 */

public class FirstFinishedThreadTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(new MyTask("Task 1", 3000));
        tasks.add(new MyTask("Task 2", 4000));
        tasks.add(new MyTask("Task 3", 1000));

        try {
            String result = executor.invokeAny(tasks);
            System.out.println("第一个执行完成的线程是： " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}

class MyTask implements Callable<String> {
    private final String name;
    private final long sleepTime;

    public MyTask(String name, long sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        return name + "，用时：" + sleepTime + "秒";
    }
}
