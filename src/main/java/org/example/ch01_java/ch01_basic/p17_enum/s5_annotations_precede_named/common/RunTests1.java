package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: Program to process marker annotations
 */
public class RunTests1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.common.Sample1");
        for (Method m : testClass.getDeclaredMethods()) {
            // isAnnotationPresent方法告知测试工具要运行哪些方法
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    // 通过调用Method.invoke反射式地运行类中所有标注了Test注解的方法
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    // 如果测试方法抛出异常，反射机制就会将它封装在InvocationTargetException中，该工具捕捉到这个异常并打印失败报告，包含测试方法抛出的原始异常
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m.getName() + " failed: " + exc);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + m.getName());
                }
            }
        }
        System.out.printf("%nPassed: %d, Failed: %d%n", passed, tests - passed);
    }
}
