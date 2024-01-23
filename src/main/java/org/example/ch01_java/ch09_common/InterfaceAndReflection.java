package org.example.ch01_java.ch09_common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author: whtli
 * @date: 2024/01/23
 * @description: 接口优先于反射
 * 样例代码的意义：
 * 如果只是以非常有限的形式使用反射机制，虽然要付出少许代价，但是可以获得许多好处
 * 用反射方式创建实例，然后通过它们的接口或者超类，以正常的方式访问这些实例
 * <p>
 * 优点：
 * (1)可以很容易的变成一个通用的集合测试器，通过侵入式地操作一个或者多个集合实例，并检查是否遵守Set接口的约定，以此来验证指定的Set实现
 * (2)可以变成一个通用的集合性能分析工具
 * <p>
 * 缺点：
 * (1)产生六个运行时异常，如果不使用反射方式的实例化，这六个错误都会成为编译时错误
 * (2)根据类名生成它的实例需要25行冗长的代码，而调用一个构造器则可以非常简洁地中石油一行代码，程序的长度可以通过捕捉ReflectiveOperationException异常来减少，这是Java7中引入的各种反射异常的一个超类
 */
public class InterfaceAndReflection {
    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<? extends Set<String>> clazz = null;
        try {
            clazz = (Class<? extends Set<String>>) Class.forName("java.util.HashSet");
        } catch (ClassNotFoundException e) {
            fatalError("Class not found");
        }
        // Get the constructor
        Constructor<? extends Set<String>> constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("No parameterless constructor");
        }
        // Instantiate the set
        Set<String> set = null;
        try {
            set = constructor.newInstance();
        } catch (InstantiationException e) {
            fatalError("Constructor not accessible");
        } catch (IllegalAccessException e) {
            fatalError("Class not instantiable");
        } catch (InvocationTargetException e) {
            fatalError("Constructor threw " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Class doesn't implement Set");
        }
        // Exercise the set
        set.addAll(Arrays.asList("1", "5", "4", "4", "3", "2", "1").subList(1, 7));
        System.out.println(set);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
