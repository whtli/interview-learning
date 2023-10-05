package org.example.java.collections.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: whtli
 * @date: 2023/10/05
 * @description: DelayQueue相关
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayedTask(3000, () -> System.out.println("Task2")));
        delayQueue.add(new DelayedTask(1000, () -> System.out.println("Task1")));
        delayQueue.add(new DelayedTask(7000, () -> System.out.println("Task3")));
        while (!delayQueue.isEmpty()) {
            // 阻塞获取最先到期的任务
            DelayedTask task = null;
            try {
                task = delayQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (task != null) {
                task.execute();
            }
        }
    }
}

class DelayedTask implements Delayed {
    /**
     * 任务到期时间
     */
    private long duration;

    /**
     * 任务
     */
    private Runnable task;

    public DelayedTask(long duration, Runnable task) {
        this.duration = System.currentTimeMillis() + duration;
        this.task = task;
    }


    // Delayed接口定义了元素的剩余延迟时间(getDelay)和元素之间的比较规则(该接口继承了Comparable接口)
    // 若希望元素能够存放到DelayQueue中，就必须实现Delayed接口的getDelay()方法和compareTo()方法，否则DelayQueue无法得知当前任务剩余时长和任务优先级的比较

    /**
     * 查看当前任务还有多久到期
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 重写compareTo使任务按照到期时间升序入队
     */
    @Override
    public int compareTo(Delayed o) {
        return Long.compare(duration, ((DelayedTask) o).duration);
    }

    public void execute() {
        task.run();
    }
}
