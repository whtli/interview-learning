package org.example.ch01_java.ch04_concurrent.p03_lock;

/**
 * @author: whtli
 * @date: 2023/10/07
 * @description: synchronized关键字的适用位置
 * synchronized同步语句块的实现使用的是monitorenter和monitorexit指令，monitorenter指令指向同步代码块的开始位置，monitorexit指令则指明同步代码块的结束位置
 * synchronized修饰的方法的实现使用的是ACC_SYNCHRONIZED标识，该标识指明了该方法是一个同步方法
 * 以上两者的本质都是获取对象监视器
 */
public class SynchronizedTest {
    /**
     * 构造方法不能使用synchronized修饰
     */
    public SynchronizedTest() {
    }

    /**
     * 修饰实例方法 （锁当前对象实例）
     */
    public synchronized void method1() {

    }

    /**
     * 修饰静态方法 （锁当前类）
     */
    public synchronized static void method2() {

    }

    /**
     * 修饰代码块 （锁指定对象）
     */
    private static final Object resource = new Object();

    public void method3() {
        // 进入同步代码库前要获得给定对象的锁
        synchronized (resource) {
            System.out.println("whtli");
        }
    }

    /**
     * 修饰代码块 （锁指定类）
     */
    public void method4() {
        // 进入同步代码库前要获得给定类的锁
        synchronized (SynchronizedTest.class) {
            System.out.println("whtli");
        }
    }
}