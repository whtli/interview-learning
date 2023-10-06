package org.example.ch01_java.ch04_concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: whtli
 * @date: 2023/08/24
 * @description: 实现一把互斥锁
 */
public class MutexLockTest {
    public static void main(String[] args) {
        MutexLockTest mutexLockTest = new MutexLockTest();
        mutexLockTest.lock();
        System.out.println("test");
        mutexLockTest.unlock();
    }

    /**
     * 创建一个静态内部类Sync继承AQS
     * 重写AQS的tryAcquire和tryRelease方法
     * 创建一个Sync对象，实现加锁方法需要调用AQS的acquire方法，释放锁方法需要调用AQS的release方法
     */
    public static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 尝试获取锁，设置独享线程为当前线程
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                System.out.println("tryAcquire");
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 因为只有一个线程会加锁成功，所以当释放锁时，设置独享线程为null
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            System.out.println("tryRelease");
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 当state为0时，表示锁没有被占用，当为1时，表示锁已被占用
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    /**
     * 创建一个Sync对象
     */
    private final Sync sync = new Sync();

    /**
     * 获取锁
     */
    public void lock() {
        System.out.println("lock");
        sync.acquire(1);
    }

    /**
     * 释放锁
     */
    public void unlock() {
        System.out.println("unlock");
        sync.release(1);
    }
}
