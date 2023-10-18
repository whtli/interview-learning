package org.example.ch01_java.ch07_feature.java10.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 集合功能增强
 */
public class EnhancedCollectionTest {
    public static void main(String[] args) {
        var origin = new ArrayList<>(Arrays.asList(1, 2, 4, 2, 1));
        // 将流中的元素收集为不可变的列表
        List<Integer> list = origin.stream().collect(Collectors.toUnmodifiableList());
        // 将流中的元素收集为不可变的集合
        Set<Integer> set = origin.stream().collect(Collectors.toUnmodifiableSet());
        System.out.println("origin: " + origin);
        System.out.println("list  : " + list);
        System.out.println("set   : " + set);
    }
}
