package org.example.ch01_java.ch06_jvm;

/**
 * @author: whtli
 * @date: 2023/07/15
 * @description: 类加载器
 */
public class PrintClassLoaderTree {

    public static void main(String[] args) {

        // Spring获取线程线程上下文类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println();

        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();

        StringBuilder split = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue) {
            System.out.println(split.toString() + classLoader);
            if (classLoader == null) {
                needContinue = false;
            } else {
                classLoader = classLoader.getParent();
                split.insert(0, "\t");
            }
        }
    }
}
