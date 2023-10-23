package org.example.other;

/**
 * @author: whtli
 * @date: 2023/09/26
 * @description: 迅雷笔试题，前缀和
 */
public class PrefixSum {
    public static int countPartitions(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];

        // 计算累积和
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int count = 0;

        // 遍历第一个子数组的结束位置
        for (int i = 0; i < n - 2; i++) {
            // 遍历第二个子数组的结束位置
            for (int j = i + 1; j < n - 1; j++) {
                int sum1 = prefixSum[i];
                int sum2 = prefixSum[j] - prefixSum[i];
                int sum3 = prefixSum[n - 1] - prefixSum[j];

                // 检查条件是否满足
                if (sum1 <= sum2 && sum2 <= sum3) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 2, 2, 2, 5, 0};
        int[] nums = {1, 1, 1};
        int result = countPartitions(nums);
        System.out.println("有 " + result + " 种分割方案。");
    }
}

