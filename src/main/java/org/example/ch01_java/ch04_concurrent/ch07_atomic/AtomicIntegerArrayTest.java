package org.example.ch01_java.ch04_concurrent.ch07_atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicIntegerArray功能测试
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int value = 0;
        int[] nums = {1, 2, 3, 4, 5, 6};
        AtomicIntegerArray array = new AtomicIntegerArray(nums);
        for (int j = 0; j < nums.length; j++) {
            System.out.print(array.get(j) + " ");
        }
        System.out.println();
        value = array.getAndSet(0, 10);
        System.out.println("value: " + value + ", array: " + array);
        value = array.getAndIncrement(0);
        System.out.println("value: " + value + ", array: " + array);
        value = array.getAndDecrement(0);
        System.out.println("value: " + value + ", array: " + array);
        value = array.addAndGet(3, 5);
        System.out.println("value: " + value + ", array: " + array);
        boolean ans = array.compareAndSet(3, 9, 11);
        System.out.println("ans: " + ans + ", array: " + array);
    }
}
