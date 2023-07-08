package org.example.sort;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/06
 * @description:
 */
public class BubboSort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = nums.length;
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < n - i; j ++) {
                if (nums[j] > nums[j + 1]) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
        }
        Arrays.stream(nums).forEach(System.out::print);
    }
}
