package org.example.java.basic.ch14_unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: Unsafe-Class操作
 * Unsafe 对Class的相关操作主要包括类加载和静态变量的操作方法
 */
public class ClassTest {
    public static void main(String[] args) {
        try {
            staticTest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void staticTest() throws Exception {
        Unsafe unsafe = reflectGetUnsafe();
        User user = new User();
        System.out.println(unsafe.shouldBeInitialized(User.class));

        Field nameField = User.class.getDeclaredField("name");
        long fieldOffset = unsafe.staticFieldOffset(nameField);
        Object fieldBase = unsafe.staticFieldBase(nameField);
        Object object = unsafe.getObject(fieldBase, fieldOffset);
        System.out.println(object);
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

@Data
class User {
    public static String name = "whtli";
    int age;
}