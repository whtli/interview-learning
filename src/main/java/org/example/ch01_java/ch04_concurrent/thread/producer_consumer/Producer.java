package org.example.ch01_java.ch04_concurrent.thread.producer_consumer;

import java.util.Random;

/**
 * @author: whtli
 * @date: 2023/04/09
 * @description:
 */
public class Producer implements Runnable {
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