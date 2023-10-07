package org.example.ch01_java.ch04_concurrent.producer_consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/04/09
 * @description:
 */
public class SyncStack {
    List<Integer> list = new ArrayList<>();
    final int capacity = 10;
    public volatile int index;

    /**
     * 供生产者调用
     */
    public synchronized void push(String producerName, int value) {
        while (list.size() >= capacity) {
            try {
                System.out.println("仓库已满 ---> 生产者--进入wait状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(value);
        System.out.println("生产者--" + producerName + "--生产了:" + value);
        notifyAll();
    }

    /**
     * 供消费者调用
     */
    public synchronized int pop(String consumerName) {
        int val = 0;
        while (list.size() == 0) {
            try {
                System.out.println(" 仓库无货 ---> 消费者--进入wait状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果有数据，继续consume
        val = list.remove(0);
        System.out.println("   消费者------" + consumerName + "--消费了:" + val);
        notifyAll();
        return val;
    }
}
