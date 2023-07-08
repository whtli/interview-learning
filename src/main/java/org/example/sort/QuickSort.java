package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] nums2 = {3, 2, 1, 5, 6, 4};
        int[] nums3 = {1, 2, 3, 8, 4, 5, 7};
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n3 = nums3.length;
        Sort1 sort1 = new Sort1();
        Sort2 sort2 = new Sort2();
        Sort3 sort3 = new Sort3();
        sort1.quickSort(nums1, 0, n1 - 1);
        sort2.quickSort(nums2, 0, n2 - 1);
        sort3.quickSort(nums3, 0, n3 - 1);
        Arrays.stream(nums1).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums2).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums3).forEach(System.out::print);
    }


}

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
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[r] >= nums[base]) {
                r--;
            }
            while (l < r && nums[l] <= nums[base]) {
                l++;
            }
            swap(nums, l, r);
        }
        swap(nums, l, base);
        return l;

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

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
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[r] >= nums[base]) {
                r--;
            }
            while (l < r && nums[l] <= nums[base]) {
                l++;
            }
            swap(nums, l, r);
        }
        swap(nums, l, base);
        return l;

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

class Sort3 {
    /* 快速排序（尾递归优化） */
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
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[r] >= nums[base]) {
                r--;
            }
            while (l < r && nums[l] <= nums[base]) {
                l++;
            }
            swap(nums, l, r);
        }
        swap(nums, l, base);
        return l;

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
