package org.example.ch01_java.ch04_concurrent.p08_relate_problems.producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: whtli
 * @date: 2023/04/09
 * @description: 生产者消费者
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        SyncStack stack = new SyncStack();

        Producer producer1 = new Producer("Producer1", stack);
        Producer producer2 = new Producer("Producer2", stack);
        Producer producer3 = new Producer("Producer3", stack);

        Consumer consumer1 = new Consumer("Consumer1", stack);
        Consumer consumer2 = new Consumer("Consumer2", stack);
        Consumer consumer3 = new Consumer("Consumer3", stack);

        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(producer3).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();
    }
}

class Producer implements Runnable {
    private String name;
    private SyncStack stack;

    public Producer(String name, SyncStack stack) {
        this.name = name;
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            int value = new Random().nextInt(100);
            stack.push(name, value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private String name;
    private SyncStack stack;

    public Consumer(String name, SyncStack stack) {
        this.name = name;
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                int val = stack.pop(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SyncStack {
    List<Integer> list = new ArrayList<>();
    final int capacity = 10;

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
        System.out.println("生产者----" + producerName + "----生产了:" + value);
        notifyAll();
    }

    /**
     * 供消费者调用
     */
    public synchronized int pop(String consumerName) {
        int val = 0;
        while (list.size() == 0) {
            try {
                System.out.println("    仓库无货 ---> 消费者--进入wait状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果有数据，继续消费
        val = list.remove(0);
        System.out.println("    消费者----" + consumerName + "----消费了:" + val);
        notifyAll();
        return val;
    }
}
