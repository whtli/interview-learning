package org.example.ch01_java.ch01_basic.p14_unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: Unsafe-对象操作
 */
public class ObjectTest {
    public static void main(String[] args) {
        Unsafe unsafe3 = reflectGetUnsafe();
        try {
            objTest(unsafe3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void objTest(Unsafe unsafe) throws Exception {
        System.out.println("-------------A-------------");
        A a1 = new A();
        System.out.println(a1.getA());
        A a2 = A.class.newInstance();
        System.out.println(a2.getA());
        // 通过allocateInstance方法创建对象过程中，不会调用类的构造方法
        // 如果想要跳过对象的初始化阶段或者跳过构造器的安全检查，可以使用这种方法
        A a3 = (A) unsafe.allocateInstance(A.class);
        System.out.println(a3.getA());


        System.out.println("\n-------------B-------------");
        // B类的构造函数为private类型，将无法通过构造函数和反射创建对象（可以通过构造函数对象setAccessible后创建对象），但allocateInstance方法仍然有效
        // 由于这种特性，allocateInstance 在 java.lang.invoke、Objenesis（提供绕过类构造器的对象生成方式）、Gson（反序列化时用到）中都有相应的应用
        // B b1 = new B();
        // System.out.println(b1.getB());
        // B b2 = B.class.newInstance();
        // System.out.println(b2.getB());
        B b3 = (B) unsafe.allocateInstance(B.class);
        System.out.println(b3.getB());

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
class A {
    private int a;

    public A() {
        this.a = 1;
    }
}

@Data
class B {
    private int b;

    private B() {
        this.b = 2;
    }
}