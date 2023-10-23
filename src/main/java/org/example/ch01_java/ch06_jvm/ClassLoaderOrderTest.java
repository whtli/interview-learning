package org.example.ch01_java.ch06_jvm;

/**
 * @author: whtli
 * @date: 2023/08/11
 * @description: 类加载过程
 */
public class ClassLoaderOrderTest {
    public static void main(String[] args) throws Exception {
        // 判断输出顺序
        Test test = new Test();
    }
}

class Test {
    static Test t1 = new Test();
    static Test t2 = new Test();

    {
        System.out.println("代码块");
    }

    static {
        System.out.println("静态块");
    }
}
