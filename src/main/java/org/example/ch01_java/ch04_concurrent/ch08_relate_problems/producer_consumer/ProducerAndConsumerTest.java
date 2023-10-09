package org.example.ch01_java.ch04_concurrent.ch08_relate_problems.producer_consumer;

/**
 * @author: whtli
 * @date: 2023/04/09
 * @description: 生产者消费者
 */
public class ProducerAndConsumerTest {
    private static final int FULL = 10;
    private static int count = 0;
    private final Object object = new Object();

    public static void main(String[] args) {
        ProducerAndConsumerTest test1 = new ProducerAndConsumerTest();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    while (count == 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者 : " + Thread.currentThread().getName() + " 刚消费了数据，现在有 " + count + "条数据");
                    object.notifyAll();
                }
            }
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    while (count == FULL) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产者 : " + Thread.currentThread().getName() + " 刚生产了数据，现在有 " + count + "条数据");
                    object.notifyAll();
                }
            }
        }
    }
}
