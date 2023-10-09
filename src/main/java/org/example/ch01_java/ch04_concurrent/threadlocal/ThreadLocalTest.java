package org.example.ch01_java.ch04_concurrent.threadlocal;

import java.util.ArrayList;
import java.util.List;
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
