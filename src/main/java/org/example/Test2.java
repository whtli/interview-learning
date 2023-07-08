package org.example;

/**
 * @author: whtli
 * @date: 2023/04/12
 * @description:
 */
public class Test2 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        Solution solution = new Solution();
        double ans = solution.findMedian(nums);
        System.out.println(ans);
    }
}

class Solution {
    public double findMedian(int[] nums) {
        int n = nums.length;
        int k = n / 2 + 1;
        quickSort(nums, 0, n - 1, n - k);
        for (int item : nums) {
            System.out.print(item + " ");
        }
        System.out.println();
        if (n % 2 != 0) {
            return nums[k - 1] * 1.0;
        } else {
            return (nums[k - 1] + nums[k - 2]) / 2.0;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int partition(int[] nums, int left, int right) {
        int base = left;
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[base]) {
                j--;
            }
            while (i < j && nums[i] <= nums[base]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, base);
        return i;
    }

    public void quickSort(int[] nums, int left, int right, int index) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        if (index == pivot) {
            return;
        }
        quickSort(nums, left, pivot - 1, index);
        quickSort(nums, pivot + 1, right, index);
    }
}