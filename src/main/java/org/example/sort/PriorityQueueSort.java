package org.example.sort;

import java.util.PriorityQueue;

/**
 * @author: whtli
 * @date: 2023/08/02
 * @description: 优先队列排序，本质是堆
 */
public class PriorityQueueSort {
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 7, 3};
        int k = 2;
        PriorityQueueSortSolution priorityQueueSortSolution = new PriorityQueueSortSolution(nums, k);
        PriorityQueue<Integer> queue = priorityQueueSortSolution.queue;
        System.out.println(queue.peek());
    }
}
 class PriorityQueueSortSolution {
     PriorityQueue<Integer> queue;
     int capacity;
     PriorityQueueSortSolution(int[] nums, int capacity) {
         this.queue = new PriorityQueue<>();
         this.capacity = capacity;
         for (int num : nums) {
             add(num);
         }
     }
     public void add(int val) {
         this.queue.offer(val);
         if (this.queue.size() > capacity) {
             this.queue.poll();
         }
     }
 }
