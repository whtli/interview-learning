package org.example.sort;

/**
 * @author: whtli
 * @date: 2023/04/07
 * @description: 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = nums.length;
        for (int i = 0; i < n - 1; i ++) {
            int base = i;

            for (int j = base + 1; j < n; j ++) {
                if (nums[j] < nums[base]) {
                    base = j;
                }
            }
            if (i != base) {
                int t = nums[base];
                nums[base] = nums[i];
                nums[i] = t;
            }
        }
        // 输出检查
        for (int item : nums) {
            System.out.print(item + " ");
        }
    }
}
