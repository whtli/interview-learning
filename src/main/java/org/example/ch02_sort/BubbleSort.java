package org.example.ch02_sort;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description: 冒泡排序
 * 想象冒泡的过程是从底部，慢慢的气泡变大直到浮出水面
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 3, 5, 2};
        int[] nums2 = {4, 1, 3, 5, 2};
        BubbleSortSolution solution = new BubbleSortSolution();
        solution.bubbleSort1(nums1);
        System.out.println();
        solution.bubbleSort2(nums2);
    }
}

class BubbleSortSolution {
    /**
     * 经典冒泡
     */
    public void bubbleSort1(int[] nums) {
        int n = nums.length;
        // 外层循环，表示需要 n-1 次冒泡，每次冒泡结束确定一个最大/最小值的位置
        for (int i = 0; i < n - 1; i++) {
            // 内层循环，找到未排序区间内元素继续冒泡
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
        }
        // 输出检查
        for (int item : nums) {
            System.out.print(item + " ");
        }
    }

    /**
     * 冒泡排序（标识优化）
     * @param nums
     */
    public void bubbleSort2(int[] nums) {
        int n = nums.length;
        // 外层循环，表示需要 n-1 次冒泡，每次冒泡结束确定一个最大/最小值的位置
        for (int i = 0; i < n - 1; i++) {
            // 元素交换行为标识
            boolean flag = false;
            // 内层循环，找到未排序区间内元素继续冒泡
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                    // 记录发生过元素交换
                    flag = true;
                }
            }
            if (!flag) {
                // 当前轮次冒泡未交换任何元素，说明已经有序
                break;
            }
        }
        // 输出检查
        for (int item : nums) {
            System.out.print(item + " ");
        }
    }
}