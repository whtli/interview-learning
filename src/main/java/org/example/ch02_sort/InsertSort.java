package org.example.ch02_sort;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description: 插入排序
 * 核心思想是将待排序的数组分成两部分：已排序部分和未排序部分
 * 初始时已排序部分只包含第一个元素，而未排序部分包含其余所有元素。然后逐步将未排序部分的元素插入到已排序部分，使得已排序部分始终保持有序。
 * 1. 从第二个元素开始，将其视为当前要插入的元素。
 * 2. 将当前元素与已排序部分的元素依次比较，直到找到合适的插入位置或者遍历完已排序部分。
 * 3. 插入当前元素到合适的位置，使得已排序部分仍然保持有序。
 * 4. 重复上述步骤，逐步将未排序部分的元素插入到已排序部分，直到整个数组都有序为止。
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 5, 2};
        int n = nums.length;

        System.out.print("排序前数组: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        for (int i = 1; i < n; i++) {
            int k = nums[i];
            int j = i - 1;
            while (j >= 0 && k < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = k;
        }

        System.out.print("\n排序后数组: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
