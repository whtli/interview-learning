package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/07
 * @description: 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.print("排序前：");
        for (int i : nums) {
            System.out.print(i + " ");
        }
        int n = nums.length;
        int[] temp = new int[n];
        MergeSortSolution solution = new MergeSortSolution();
        solution.mergeSort(nums, 0, n - 1, temp);

        System.out.print("\n排序后：");
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}

class MergeSortSolution {
    /**
     * 归并排序
     * 从顶至底递归地将数组从中点切为两个子数组，直至长度为 1
     * 与二叉树后续遍历的方式一样
     */
    public void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 终止条件：当子数组长度为 1 时终止递归
        if (left >= right) {
            return;
        }
        // 划分阶段
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        if (nums[mid] <= nums[mid + 1]) {
            // 当左子数组的最大值小于右子数组的最小值时，说明数组已经是有序的，无需合并
            return;
        }
        // 合并阶段
        merge(nums, left, mid, right, temp);
    }

    /**
     * 合并左子数组和右子数组
     * 合并任务本质是要将两个有序子数组合并为一个有序数组。
     * 左子数组区间 [left, mid]
     * 右子数组区间 [mid + 1, right]
     */
    public void merge(int[] nums, int left, int mid, int right, int[] temp) {
        // 将数组复制到临时数组中
        temp = Arrays.copyOfRange(nums, left, right + 1);

        int i = left, j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == right + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
            }
        }
    }
}
