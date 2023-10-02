package org.example.java.basic.ch14_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: Unsafe-内存屏障
 */
public class LoadFenceTest {
    public static void main(String[] args) {
        // 内存屏障
        Unsafe unsafe2 = reflectGetUnsafe();
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();
        while (true) {
            boolean flag = changeThread.isFlag();
            // 加入读内存屏障
            unsafe2.loadFence();
            // 感知flag的变化
            // 如果删掉loadFence方法，那么主线程将无法感知到flag发生的变化，会一直在while中循环
            if (flag) {
                System.out.println("detected flag changed");
                break;
            }
        }
        System.out.println("main thread is over");
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
/**
 * 内存屏障
 */
class ChangeThread implements Runnable {
    boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("subThread change flag to:" + flag);
    }
}