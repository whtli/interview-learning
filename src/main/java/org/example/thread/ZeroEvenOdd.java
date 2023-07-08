package org.example.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author: whtli
 * @date: 2023/04/05
 * @description:
 */
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
            synchronized(obj) {
                while (flag != 0) {
                    obj.wait();
                }
                printNumber.accept(0);
                num ++;
                flag = num % 2 == 1 ? 1 : 2;
                obj.notifyAll();
            }
        }
    }
    public void even(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized(obj) {
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
            synchronized(obj) {
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
