package org.example.ch01_java.ch08_method.p02_overload_with_caution;

import java.util.*;

/**
 * @author: whtli
 * @date: 2023/12/26
 * @description: 自动装箱机制引发的问题，Java添加了泛型和自动装箱之后，破坏了List接口
 */
public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            // 错误的方法调用形式
            // list.remove(i);

            // 正确的方法调用形式1
            // list.remove((Integer) i);
            // 正确的方法调用形式2
            list.remove(Integer.valueOf(i));
        }
        System.out.println(set + " " + list);
    }
}
