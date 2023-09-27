package org.example.pattern.singleton;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description: 单例模式的不同实现方式
 * 饿汉式和懒汉式
 * 饿和懒指的是类加载时是否创建单例而言
 */


public class Singleton {

}

// 饿汉式，一开始就生成
class Singleton1 {
    public static final Singleton1 singleton1 = new Singleton1();

    public Singleton1() {
    }

    public static Singleton1 getInstance() {
        return singleton1;
    }
}

// 懒汉式，需要时才生成
class Singleton2 {
    public static Singleton2 singleton2;

    public Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

// 双重校验锁，懒汉式为基础，线程安全
class Singleton3 {
    public static volatile Singleton3 uniqueInstance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            // 类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton3();
                }
            }
        }
        return uniqueInstance;
    }
}

// 静态内部类，懒汉式为基础，线程安全。
class Singleton4 {
    public Singleton4() {
    }

    private static class SingletonHolder {
        private static final Singleton4 singleton4 = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.singleton4;
    }
}