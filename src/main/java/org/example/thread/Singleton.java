package org.example.thread;

/**
 * @author: whtli
 * @date: 2023/03/05
 * @description: 双重校验锁实现对象单例（线程安全）
 */
public class Singleton {
    public volatile static Singleton uniqueInstance;

    private Singleton() {

    }

    public Singleton getUniqueInstance() {
        // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            // 类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
