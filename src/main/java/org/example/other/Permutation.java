package org.example.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Li Qiang
 * @date: 2023/7/26
 * @description: 全排列问题
 * 非递归实现（只适用于元素无重复的情况）
 * 递归实现
 */
public class Permutation {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] nums1 = {1, 2, 3, 3};
        List<List<Integer>> permutations1 = solution1.permute(nums1);
        // 输出验证
        /*for (List<Integer> permutation : permutations1) {
            System.out.println(permutation);
        }*/
        System.out.println(permutations1.size());
        System.out.println();

        Solution2 solution2 = new Solution2();
        int[] nums2 = {1, 2, 3, 3};
        List<List<Integer>> permutations2 = solution2.permute(nums2);
        // 输出验证
        /*for (List<Integer> permutation : permutations2) {
            System.out.println(permutation);
        }*/
        System.out.println(permutations2.size());
        System.out.println();
    }
}

/**
 * 全排列问题，非递归实现
 */
class Solution1 {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        // 定义结果列表
        List<List<Integer>> ans = new ArrayList<>();
        // 借助栈模拟递归过程
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());

        while (!stack.isEmpty()) {
            // currentPath代表当前的排列（不一定是全排列）
            // 其中的元素代表已将被当前排列采用的元素
            List<Integer> currentPath = stack.pop();
            if (currentPath.size() == n) {
                // 如果当前的排列是全排列，将其添加到结果列表中
                ans.add(currentPath);
            } else {
                // 如果当前的排列没有完成，通过添加未使用的元素来补充它
                for (int element : nums) {
                    if (!currentPath.contains(element)) {
                        // 如果当前排列的元素中不包含element，则采纳当前数组元素并将其放进新排列中
                        List<Integer> newPath = new ArrayList<>(currentPath);
                        newPath.add(element);
                        // 新的排列（不一定是全排列）暂时压入栈中
                        stack.push(newPath);
                    }
                }
            }
        }
        return ans;
    }
}

/**
 * 全排列问题，递归实现
 */
class Solution2 {
    List<List<Integer>> ans;
    boolean[] visited;
    List<Integer> path;

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        ans = new ArrayList<>();
        visited = new boolean[n];
        path = new ArrayList<>();

        loop(nums, 0, n);
        return ans;

    }

    public void loop(int[] nums, int len, int n) {
        if (len == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(nums[i]);
                loop(nums, len + 1, n);
                // 回溯
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
}
