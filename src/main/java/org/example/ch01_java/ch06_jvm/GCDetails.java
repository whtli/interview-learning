package org.example.ch01_java.ch06_jvm;

/**
 * @author: whtli
 * @date: 2023/07/15
 * @description: JVM垃圾回收详解
 * 需要添加VM options：-XX:+PrintGCDetails
 */
public class GCDetails {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[32000 * 1024];
        allocation2 = new byte[1000 * 1024];
        allocation3 = new byte[1000 * 1024];
        allocation4 = new byte[1000 * 1024];
        allocation5 = new byte[1000 * 1024];
    }
}
