package org.example.ch01_java.ch07_feature.java9.process;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 进程相关API
 */
public class ProcessAPITest {
    public static void main(String[] args) {
        // 获取当前正在运行的JVM的进程
        ProcessHandle handle = ProcessHandle.current();
        System.out.println("进程的id：" + handle.pid());
        System.out.println("进程的信息：" + handle.info());
        System.out.println("进程的父进程：" + handle.parent());
        System.out.println("进程的存活状态：" + handle.isAlive());
    }
}
