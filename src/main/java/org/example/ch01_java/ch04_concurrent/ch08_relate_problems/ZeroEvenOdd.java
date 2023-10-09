package org.example.ch01_java.ch04_concurrent.ch08_relate_problems;

import java.util.function.IntConsumer;

/**
 * @author: whtli
 * @date: 2023/04/05
 * @description: 三个线程，分别打印0、奇数、偶数，形式为：0102030405……
 * @source: https://leetcode.cn/problems/print-zero-even-odd/description/
 */

// synchronized
public class ZeroEvenOdd {
    private int n;
    private volatile int flag;
    private volatile int num;
    private Object obj;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.flag = 0;
        this.num = 0;
        this.obj = new Object();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (obj) {
                while (flag != 0) {
                    obj.wait();
                }
                printNumber.accept(0);
                num++;
                flag = num % 2 == 1 ? 1 : 2;
                obj.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (obj) {
                while (flag != 2) {
                    obj.wait();
                }
                printNumber.accept(num);
                flag = 0;
                obj.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (obj) {
                while (flag != 1) {
                    obj.wait();
                }
                printNumber.accept(num);
                flag = 0;
                obj.notifyAll();
            }
        }
    }
}

/*
// 信号量
class ZeroEvenOdd {
    private int n;
    private Semaphore flag0, flag1, flag2;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.flag0 = new Semaphore(1);
        this.flag1 = new Semaphore(0);
        this.flag2 = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            flag0.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                flag1.release();
            } else {
                flag2.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            flag2.acquire();
            printNumber.accept(i);
            flag0.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            flag1.acquire();
            printNumber.accept(i);
            flag0.release();
        }
    }
}*/

/*
// Lock
class ZeroEvenOdd {
    private int n;
    private int flag;
    private Lock lock;
    private Condition cond;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.flag = 0;
        this.lock = new ReentrantLock();
        this.cond = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i ++) {
            lock.lock();
            try {
                while (flag != 0) {
                    cond.await();
                }
                printNumber.accept(0);
                if (i % 2 == 1) {
                    flag = 1;
                } else {
                    flag = 2;
                }
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                while (flag != 2) {
                    cond.await();
                }
                printNumber.accept(i);
                flag = 0;
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                while (flag != 1) {
                    cond.await();
                }
                printNumber.accept(i);
                flag = 0;
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}*/
