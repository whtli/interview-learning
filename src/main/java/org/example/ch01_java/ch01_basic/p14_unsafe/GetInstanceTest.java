package org.example.ch01_java.ch01_basic.p14_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: Unsafe-获取实例
 */

public class GetInstanceTest {
    public static void main(String[] args) {
        // 获取Unsafe实例
        Unsafe unsafe1 = reflectGetUnsafe();
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





