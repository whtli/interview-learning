package org.example.ch02_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/04/07
 * @description: 桶排序
 */
public class BucketsSort {
    public static void main(String[] args) {
        double[] nums = {0.49, 0.96, 0.82, 0.09, 0.57, 0.43, 0.91, 0.75, 0.15, 0.37};
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }
        // 1. 将数组元素分配到各个桶中
        for (double num : nums) {
            int t = (int) num * k;
            buckets.get(t).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Double> bucket : buckets) {
            Collections.sort(bucket);
        }
        // 3. 遍历桶合并结果
        int i = 0;
        for (List<Double> bucket : buckets) {
            for (double item : bucket) {
                nums[i++] = item;
            }
        }
        // 输出检查
        for (double item : nums) {
            System.out.print(item + " ");
        }
    }
}
