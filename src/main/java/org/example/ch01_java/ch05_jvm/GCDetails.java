package org.example.ch01_java.ch05_jvm;

/**
 * @author: whtli
 * @date: 2023/07/15
 * @description: JVM垃圾回收详解
 */
public class GCDetails {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[2000000000*2000];
    }
}
