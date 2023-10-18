package org.example.ch01_java.ch07_feature.java9.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 快速创建不可变集合
 */
public class QuickCreateCollectionsTest {
    public static void main(String[] args) {
        List<String> list = List.of("Java", "C++");
        Set<String> set = Set.of("Java", "C++");
        Map<String, Integer> map = Map.of("Java", 1, "C++", 2);
        // 异常：java.lang.UnsupportedOperationException
        // 因为使用of()创建的集合为不可变集合，不能进行添加、删除、替换、排序等操作
        // list.add("w2ww");
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
    }
}
