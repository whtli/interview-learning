package org.example.ch01_java.ch04_concurrent;

/**
 * @author: whtli
 * @date: 2023/04/02
 * @description:
 */
public class SynchronizedLockTest {
    private static Object resource = new Object();
    public static void main(String[] args) {
        synchronized (resource) {

        }
    }
}
