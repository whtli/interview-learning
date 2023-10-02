package org.example.java.basic.ch14_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: Unsafe-内存操作
 */
public class MemoryTest {
    public static void main(String[] args) {
        Unsafe unsafe1 = reflectGetUnsafe();
        memoryTest(unsafe1);
    }

    /**
     * 内存操作
     */
    private static void memoryTest(Unsafe unsafe) {
        int size = 4;
        long addr = unsafe.allocateMemory(size);
        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        System.out.println("addr:  " + addr);
        System.out.println("addr3: " + addr3);
        try {
            unsafe.setMemory(null, addr, size, (byte) 1);
            for (int i = 0; i < 2; i++) {
                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        } finally {
            System.out.println();
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);
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
