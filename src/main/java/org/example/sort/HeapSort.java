package org.example.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description: 堆排序
 */

public class HeapSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        MaxHeap maxHeap = new MaxHeap(nums);
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.pop() + " ");
        }
        System.out.println(maxHeap.isEmpty());

        MinHeap minHeap = new MinHeap(nums);
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.pop() + " ");
        }
        System.out.println(minHeap.isEmpty());
    }
}

/**
 * 小顶堆
 */
class MinHeap {
    List<Integer> minHeap;

    /* 构造方法，根据输入列表建堆 */
    MinHeap(int[] nums) {
        minHeap = new ArrayList<>();
        // 将列表元素原封不动添加进堆
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }
        // 堆化除叶结点以外的其他所有结点
        for (int i = getIndexOfParent(size() - 1); i >= 0; i--) {
            adjustDown(i);
        }
    }

    /* 获取堆大小 */
    public int size() {
        return minHeap.size();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    /* 获取左子结点索引 */
    public int getIndexOfLeftChild(int i) {
        return 2 * i + 1;
    }

    /* 获取右子结点索引 */
    public int getIndexOfRightChild(int i) {
        return 2 * i + 2;
    }

    /* 获取父结点索引 */
    public int getIndexOfParent(int i) {
        return (i - 1) / 2;
    }

    /* 访问堆顶元素 */
    public int peek() {
        return minHeap.get(0);
    }

    /* 元素出堆 */
    public int pop() {
        // 判空处理
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // 交换根节点和最后一个节点的值
        swap(0, size() - 1);
        // 删除节点
        int value = minHeap.remove(size() - 1);
        // 自顶向底堆化
        adjustDown(0);
        return value;
    }

    /* 元素入堆 */
    public void push(int value) {
        // 添加结点
        minHeap.add(value);
        // 自底至顶堆化
        adjustUp(size() - 1);
    }

    public void swap(int i, int j) {
        Collections.swap(minHeap, i, j);
    }

    /* 从结点 i 开始，从底至顶堆化 */
    public void adjustUp(int i) {
        while (true) {
            int p = getIndexOfParent(i);
            if (p < 0 || minHeap.get(p) < minHeap.get(i)) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    /* 从结点 i 开始，从顶至底堆化 */
    public void adjustDown(int i) {
        while (true) {
            int m = i;
            int l = getIndexOfLeftChild(i);
            int r = getIndexOfRightChild(i);
            if (l < size() && minHeap.get(l) < minHeap.get(m)) {
                m = l;
            }
            if (r < size() && minHeap.get(r) < minHeap.get(m)) {
                m = r;
            }
            if (m == i) {
                break;
            }
            swap(i, m);
            i = m;
        }
    }
}

/**
 * 大顶堆
 */
class MaxHeap {
    List<Integer> maxHeap;

    /* 构造方法，根据输入列表建堆 */
    MaxHeap(int[] nums) {
        maxHeap = new ArrayList<>();
        // 将列表元素原封不动添加进堆
        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
        }
        // 堆化除叶结点以外的其他所有结点
        for (int i = getIndexOfParent(size() - 1); i >= 0; i--) {
            adjustDown(i);
        }
    }

    /* 获取堆大小 */
    public int size() {
        return maxHeap.size();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /* 获取左子结点索引 */
    public int getIndexOfLeftChild(int i) {
        return 2 * i + 1;
    }

    /* 获取右子结点索引 */
    public int getIndexOfRightChild(int i) {
        return 2 * i + 2;
    }

    /* 获取父结点索引 */
    public int getIndexOfParent(int i) {
        return (i - 1) / 2;
    }

    /* 访问堆顶元素 */
    public int peek() {
        return maxHeap.get(0);
    }

    /* 元素出堆 */
    public int pop() {
        // 判空处理
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // 交换根节点和最后一个节点的值
        swap(0, size() - 1);
        // 删除节点
        int value = maxHeap.remove(size() - 1);
        // 自顶向底堆化
        adjustDown(0);
        return value;
    }

    /* 元素入堆 */
    public void push(int value) {
        // 添加结点
        maxHeap.add(value);
        // 自底至顶堆化
        adjustUp(size() - 1);
    }

    public void swap(int i, int j) {
        Collections.swap(maxHeap, i, j);
    }

    /* 从结点 i 开始，从底至顶堆化 */
    public void adjustUp(int i) {
        while (true) {
            // 获取i的父节点下标
            int p = getIndexOfParent(i);
            // 当“越过根节点”或“无需再调整”时，结束堆化
            if (p < 0 || maxHeap.get(p) > maxHeap.get(i)) {
                break;
            }
            // 交换节点值
            swap(i, p);
            // 继续向上堆化
            i = p;
        }
    }

    /* 从结点 i 开始，从顶至底堆化 */
    public void adjustDown(int i) {
        while (true) {
            int m = i;
            // 获取i的孩子节点下标
            int l = getIndexOfLeftChild(i);
            int r = getIndexOfRightChild(i);
            // 当“越过末尾节点”或“无需再调整”时，结束堆化
            if (l < size() && maxHeap.get(l) > maxHeap.get(m)) {
                m = l;
            }
            if (r < size() && maxHeap.get(r) > maxHeap.get(m)) {
                m = r;
            }
            if (i == m) {
                break;
            }
            // 交换节点值
            swap(i, m);
            // 继续向下堆化
            i = m;
        }
    }
}
