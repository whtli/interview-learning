package org.example.java.basic.ch14_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: Unsafe-线程调度
 * Unsafe 类中提供了park、unpark方法进行线程调度
 * 锁和同步器框架的核心类AbstractQueuedSynchronizer(AQS)，是通过调用LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒的，
 * 而LockSupport的park、unpark方法实际是调用Unsafe的park、unpark方法实现的。
 * <p>
 * // 取消阻塞线程
 * public native void unpark(Object thread);
 * // 阻塞线程
 * public native void park(boolean isAbsolute, long time);
 */
public class ThreadDispatchTest {
    public static void main(String[] args) {
        Unsafe unsafe = reflectGetUnsafe();
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("subThread try to unpark mainThread");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("park mainThread success");
        unsafe.park(false, 0L);
        System.out.println("unpark mainThread success");
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            System.out.println("reflectGetUnsafe\n");
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
