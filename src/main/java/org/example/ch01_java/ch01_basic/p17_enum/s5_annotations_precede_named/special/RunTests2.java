package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.special;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: Program to process marker annotations
 */
public class RunTests2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.special.Sample2");
        for (Method m : testClass.getDeclaredMethods()) {
            // isAnnotationPresent方法告知测试工具要运行哪些方法
            /*if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    // 通过调用Method.invoke反射式地运行类中所有标注了Test注解的方法
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m.getName());
                } catch (InvocationTargetException wrappedExc) {
                    // 如果测试方法抛出异常，反射机制就会将它封装在InvocationTargetException中，该工具捕捉到这个异常并打印失败报告，包含测试方法抛出的原始异常
                    Throwable exc = wrappedExc.getCause();

                    // 版本1
                    *//*Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf("Test %s failed: excepted %s, got %s%n", m.getName(), excType.getName(), exc);
                    }*//*

                    // 版本2
                    *//*int oldPassed = passed;
                    Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptionTest.class).value();
                    for (Class<? extends Exception> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    // 确保抛出指定异常时才能通过
                    if (passed == oldPassed) {
                        System.out.printf("Test %s failed: %s %n", m.getName(), exc);
                    }*//*

                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + m.getName());
                }
            }*/

            // 版本3：假如可重复的注解，提升了源代码的可读性，逻辑上是将同一个注解类型的多个实例应用到了一个指定的程序元素
            if (m.isAnnotationPresent(ExceptionTest.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    // 通过调用Method.invoke反射式地运行类中所有标注了Test注解的方法
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m.getName());
                } catch (Throwable wrappedExc) {
                    // 如果测试方法抛出异常，反射机制就会将它封装在InvocationTargetException中，该工具捕捉到这个异常并打印失败报告，包含测试方法抛出的原始异常
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests = m.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    // 确保抛出指定异常时才能通过
                    if (passed == oldPassed) {
                        System.out.printf("Test %s failed: %s %n", m.getName(), exc);
                    }
                }
            }
        }
        System.out.printf("%nPassed: %d, Failed: %d%n", passed, tests - passed);
    }
}
