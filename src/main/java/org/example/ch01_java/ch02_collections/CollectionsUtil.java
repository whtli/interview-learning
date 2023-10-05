package org.example.ch01_java.ch02_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/10/05
 * @description: Collections工具类
 */
public class CollectionsUtil {
    public static void main(String[] args) {
        // 排序操作
        sortAbility();
        // 查找、替换操作
        searchAbility();
        // 同步控制

    }

    private static void searchAbility() {
        // 初始化
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 7, 2, 3, 5, 2));
        System.out.println("初始化: " + list);
        // 交换元素
        Collections.swap(list, 1, 4);
        System.out.println("交换元素: " + list);
        // 查找最大值、最小值
        int maxVal = Collections.max(list);
        System.out.println("最大值: " + maxVal);
        int minVal = Collections.min(list);
        System.out.println("最小值: " + minVal);
        // 统计元素出现次数
        int frequency = Collections.frequency(list, 2);
        System.out.println("元素2出现的次数: " + frequency);
        // 统计子列表在list中第一次出现的索引，找不到则返回-1
        int idx1 = Collections.indexOfSubList(list, new ArrayList<>(Arrays.asList(7, 2)));
        System.out.println("(7, 2)正序第一次出现的索引: " + idx1);
        int idx2 = Collections.indexOfSubList(list, new ArrayList<>(Arrays.asList(1, 2)));
        System.out.println("(1, 2)正序第一次出现的索引: " + idx2);
        int idx3 = Collections.lastIndexOfSubList(list, new ArrayList<>(Arrays.asList(2, 3)));
        System.out.println("(7, 2)逆序第一次出现的索引: " + idx3);
        int idx4 = Collections.lastIndexOfSubList(list, new ArrayList<>(Arrays.asList(1, 2)));
        System.out.println("(1, 2)逆序第一次出现的索引: " + idx4);
        // 二分查找，前提是有序
        Collections.sort(list);
        int ret1 = Collections.binarySearch(list, 0);
        System.out.println("二分查找0结果: " + ret1);
        int ret2 = Collections.binarySearch(list, 2);
        System.out.println("二分查找2结果: " + ret2);
        int ret3 = Collections.binarySearch(list, 6);
        System.out.println("二分查找6结果: " + ret3);
        // 用新元素替换旧元素，所有的都替换
        Collections.replaceAll(list, 2, 9);
        System.out.println("用新元素替换旧元素: " + list);
        // 替换/填充所有元素
        Collections.fill(list, -1);
        System.out.println("填充所有元素: " + list);
    }

    private static void sortAbility() {
        // 初始化
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 7, 2, 3, 5));
        System.out.println("初始化: " + list);
        // 反转
        Collections.reverse(list);
        System.out.println("反转: " + list);
        // 随机排序
        Collections.shuffle(list);
        System.out.println("随机排序: " + list);
        // 按自然排序的升序排序
        Collections.sort(list);
        System.out.println("按自然排序的升序排序: " + list);
        // 自定义排序
        Collections.sort(list, ((o1, o2) -> o2.compareTo(o1)));
        System.out.println("自定义排序: " + list);
        // 旋转
        Collections.rotate(list, -2);
        System.out.println("头部向后旋转: " + list);
        Collections.rotate(list, 3);
        System.out.println("尾部向前旋转: " + list);
        System.out.println();
    }
}
