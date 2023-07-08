package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/07
 * @description:
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = nums.length;
        Solution solution = new Solution();
        solution.mergeSort(nums, 0, n - 1);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}

class Solution {
    /* 合并左子数组和右子数组 */
    // 合并任务本质是要将两个有序子数组合并为一个有序数组。
    // 左子数组区间 [left, mid]
    // 右子数组区间 [mid + 1, right]
    public void merge(int[] nums, int left, int mid, int right) {
        int leftStart = left - left, leftEnd = mid - left;
        int rightStart = mid - left + 1, rightEnd = right - left;
        int[] tmp = Arrays.copyOfRange(nums, left, right + 1);

        int i = leftStart, j = rightStart;

        for (int k = left; k <= right; k ++) {
            if (j > rightEnd || tmp[i] <= tmp[j]) {
                nums[k] = tmp[i ++];
            } else {
                nums[k] = tmp[j ++];
            }
        }
    }

    /* 归并排序 */
    // 从顶至底递归地将数组从中点切为两个子数组，直至长度为 1
    // 与二叉树后续遍历的方式一样
    public void mergeSort(int[] nums, int left, int right) {
        // 终止条件
        if (left >= right) {
            // 当子数组长度为 1 时终止递归
            return;
        }
        // 划分阶段
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 合并阶段
        merge(nums, left, mid, right);
    }
}
