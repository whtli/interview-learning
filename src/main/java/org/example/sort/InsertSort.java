package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description:
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = nums.length;
        for (int i = 1; i < n; i ++) {
            int k = nums[i];
            int j = i - 1;
            while (j >= 0 && k < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = k;
        }
        Arrays.stream(nums).forEach(System.out::print);
    }
}
