package org.example.ch02_sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description: 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] nums2 = {3, 2, 1, 5, 6, 4};
        int[] nums3 = {1, 2, 3, 8, 4, 5, 7};
        int[] nums4 = {9, 2, 5, 5, 7};

        Sort1 sort1 = new Sort1();
        Sort2 sort2 = new Sort2();
        Sort3 sort3 = new Sort3();
        Sort4 sort4 = new Sort4();

        sort1.quickSort(nums1, 0, nums1.length - 1);
        sort2.quickSort(nums2, 0, nums2.length - 1);
        sort3.quickSort(nums3, 0, nums3.length - 1);
        sort4.quickSort(nums4);

        Arrays.stream(nums1).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums2).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums3).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums4).forEach(System.out::print);
    }
}

/* 哨兵划分（常规） */
class Sort1 {
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /* 哨兵划分，以 nums[left] 作为基准数 */
    public int partition(int[] nums, int left, int right) {
        int base = left;
        while (left < right) {
            while (left < right && nums[right] >= nums[base]) {
                right--;
            }
            while (left < right && nums[left] <= nums[base]) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, left, base);
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/* 哨兵划分（三数取中值） */
class Sort2 {
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    public int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left];
        int m = nums[mid];
        int r = nums[right];
        if (l < m && m < r) {
            return mid;
        } else if (m < l && l < r) {
            return left;
        } else {
            return right;
        }
    }

    /* 哨兵划分（三数取中值） */
    public int partition(int[] nums, int left, int right) {
        int base = medianThree(nums, left, (left + right) / 2, right);
        while (left < right) {
            while (left < right && nums[right] >= nums[base]) {
                right--;
            }
            while (left < right && nums[left] <= nums[base]) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, left, base);
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/* 尾递归优化 */
class Sort3 {
    public void quickSort(int[] nums, int left, int right) {
        while (left < right) {
            int pivot = partition(nums, left, right);
            // 对两个子数组中较短的那个执行快排
            if (pivot - left < right - pivot) {
                // 递归排序左子数组
                quickSort(nums, left, pivot - 1);
                // 剩余待排序区间为 [pivot + 1, right]
                left = pivot + 1;
            } else {
                // 递归排序右子数组
                quickSort(nums, pivot + 1, right);
                // 剩余待排序区间为 [left, pivot - 1]
                right = pivot - 1;
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        int base = left;
        while (left < right) {
            while (left < right && nums[right] >= nums[base]) {
                right--;
            }
            while (left < right && nums[left] <= nums[base]) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, left, base);
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/* 非递归实现 */
class Sort4 {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        Stack<Integer> stack = new Stack();
        // 初始待排序区间入栈
        stack.push(0);
        stack.push(nums.length - 1);

        while (!stack.isEmpty()) {
            // 逐对取出待排序区间
            int end = stack.pop();
            int start = stack.pop();

            // 获取哨兵位置
            int pivot = partition(nums, start, end);

            // 分治
            if (pivot - 1 > start) {
                stack.push(start);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < end) {
                stack.push(pivot + 1);
                stack.push(end);
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        int base = left;
        while (left < right) {
            while (left < right && nums[right] >= nums[base]) {
                right--;
            }
            while (left < right && nums[left] <= nums[base]) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, left, base);
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}