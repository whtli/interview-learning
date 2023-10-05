package org.example.ch01_java.ch01_basic.ch14_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: Unsafe-CAS操作
 * CAS 是一条 CPU 的原子指令（cmpxchg 指令），不会造成所谓的数据不一致问题
 */
public class CasTest {
    private volatile int a;

    Unsafe unsafe = reflectGetUnsafe();

    public static void main(String[] args) {
        CasTest casTest = new CasTest();
        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                casTest.increment(i);
                System.out.print(casTest.a + " ");
            }
        }).start();
        new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                casTest.increment(i);
                System.out.print(casTest.a + " ");
            }
        }).start();
    }

    private void increment(int x) {
        /**
         * 在调用compareAndSwapInt方法后，会直接返回true或false的修改结果，因此需要在代码中手动添加自旋的逻辑
         * 在AtomicInteger类的设计中，也是采用了将compareAndSwapInt的结果作为循环条件，直至修改成功才退出死循环的方式来实现的原子性的自增操作
         */
        while (true) {
            try {
                long fieldOffset = unsafe.objectFieldOffset(CasTest.class.getDeclaredField("a"));
                if (unsafe.compareAndSwapInt(this, fieldOffset, x - 1, x))
                    break;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
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
