package org.example;

/**
 * @author: whtli
 * @date: 2023/03/30
 * @description:
 */
public class Singleton {
    private static volatile Singleton uniqueSingleton;

    public Singleton(){}

    public static Singleton getUniqueSingleton() {
        if (uniqueSingleton == null) {
            synchronized (Singleton.class) {
                if (uniqueSingleton == null) {
                    uniqueSingleton = new Singleton();
                }
            }
        }
        return uniqueSingleton;
    }
}
