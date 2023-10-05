package org.example.java.collections.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @author: whtli
 * @date: 2023/08/15
 * @description: BlockingQueue相关
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new DelayQueue();
    }
}

class ManualBlockQueue {
    private final int capacity;
    private Queue<Integer> queue;
    public ManualBlockQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(int val) throws InterruptedException {
        while (getSize() == capacity) {
            wait();
        }
        queue.add(val);
        notifyAll();
    }

    public int dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int val = queue.poll();
        notifyAll();
        return val;
    }

    public synchronized int getSize() {
        return this.queue.size();
    }
}