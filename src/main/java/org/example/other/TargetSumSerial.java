package org.example.other;

/**
 * @author: whtli
 * @date: 2023/08/21
 * @description: 给定一个数组，和一个数字m，问这个数组中存不存在一个子序列（不一定连续），使得它的和为m，输出true或false
 */
public class TargetSumSerial {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int m = 14;

        TargetSumSerialSolution1 solution1 = new TargetSumSerialSolution1();
        boolean result1 = solution1.canFindSum(nums, m);
        // 输出 true，因为可以选择子序列 [4, 5]
        System.out.println(result1);

        TargetSumSerialSolution2 solution2 = new TargetSumSerialSolution2();
        boolean result2 = solution2.canFindSum(nums, m);
        // 输出 true，因为可以选择子序列 [4, 5]
        System.out.println(result2);
    }
}

class TargetSumSerialSolution1 {
    public boolean canFindSum(int[] nums, int m) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        // 初始条件
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        // 动态规划
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }
}


class TargetSumSerialSolution2 {
    public boolean canFindSum(int[] nums, int m) {
        return loopCheck(nums, m, 0);
    }

    private boolean loopCheck(int[] nums, int m, int len) {
        if (m == 0) {
            return true;
        }
        if (len == nums.length) {
            return false;
        }
        // 选择包括当前元素
        if (nums[len] <= m) {
            if (loopCheck(nums, m - nums[len], len + 1)) {
                return true;
            }
        }
        // 选择不包括当前元素
        return loopCheck(nums, m, len + 1);
    }
}