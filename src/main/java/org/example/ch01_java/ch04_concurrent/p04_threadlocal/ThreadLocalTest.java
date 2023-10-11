package org.example.ch01_java.ch04_concurrent.p04_threadlocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: ThreadLocal的基本使用
 */
public class ThreadLocalTest {
    private final List<String> message = new ArrayList<>();
    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);


    public static void add(String message) {
        holder.get().message.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().message;
        holder.remove();
        System.out.println("size : " + holder.get().message.size());
        return messages;
    }

    // 斐波那契数/黄金分割数，使hash分布非常均匀
    private static AtomicInteger nextHashCode = new AtomicInteger(0);
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        ThreadLocalTest.add("interview-learning");
        System.out.println(holder.get().message);
        ThreadLocalTest.clear();

        System.out.println("\n----测试ThreadLocalMap的Hash算法----");
        for (int i = 0; i < 16; i++) {
            int hashCode = nextHashCode.getAndAdd(HASH_INCREMENT);
            int bucket = hashCode & 15;
            System.out.println(i + "在桶中的位置：" + bucket);
        }
    }
}

class ThreadLocalExample implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MMdd-HHmm"));

    @Override
    public void run() {
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // formatter的样式在此处被当前线程更改，但这个变更不会影响到其他线程
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name : " + Thread.currentThread().getName() + "; Default Formatter : " + formatter.get().toPattern());
    }

    public static void main(String[] args) {
        ThreadLocalExample obj = new ThreadLocalExample();
        // 观察输出结果可以得知，虽然Thread-i已经改变了formatter的值，但Thread-j默认格式化值与初始化值相同
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(obj, "" + i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t.start();
        }
    }
}