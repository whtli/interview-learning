package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/07
 * @description: 选择排序
 * 初始状态下，所有元素未排序，即未排序（索引）区间为[0, n-1]
 * 1. 选取区间[0, n-1]中的最小元素，将其与索引处元素交换。完成后，数组前 1 个元素已排序。
 * 2. 选取区间[1, n-1]中的最小元素，将其与索引处元素交换。完成后，数组前 2 个元素已排序。
 * 3. 以此类推。经过 n-1 轮选择与交换后，数组前 n-1 个元素已排序。
 * 4. 仅剩的一个元素必定是最大元素，无须排序，因此数组排序完成。
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = nums.length;
        // 外循环：未排序区间为 [i, n-1]
        for (int i = 0; i < n - 1; i++) {
            // 内循环：找到未排序区间内的最小元素
            int base = i;
            for (int j = base + 1; j < n; j++) {
                if (nums[j] < nums[base]) {
                    // 记录最小元素的索引
                    base = j;
                }
            }
            // 将该最小元素与未排序区间的首个元素交换
            if (i != base) {
                swap(nums, base, i);
            }
        }
        // 输出检查
        for (int item : nums) {
            System.out.print(item + " ");
        }
    }

    public static void swap(int[] nums, int base, int i) {
        int t = nums[base];
        nums[base] = nums[i];
        nums[i] = t;
    }
}
