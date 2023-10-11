package org.example.ch01_java.ch04_concurrent.p04_threadlocal;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: InheritableThreadLocalTest
 * 子线程共享父线程中创建的副本数据
 */
public class InheritableThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父类数据：threadLocal");
        inheritableThreadLocal.set("父类数据：inheritableThreadLocal");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 子线程获取父线程ThreadLocal数据" + threadLocal.get());
                System.out.println(Thread.currentThread().getName() + ": 子线程获取父线程InheritableThreadLocal数据" + inheritableThreadLocal.get());
            }
        }, "new-thread").start();
    }
}
