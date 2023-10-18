package org.example.ch01_java.ch07_feature.java10.local_variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 局部变量类型推断(var)
 */
public class CreateVariableTest {
    public static void main(String[] args) {
        var num = 0;
        var str = "hello world";
        var list = new ArrayList<>();
        var set = new HashSet<>();
        var map = new HashMap<Integer, String>();
        System.out.println(num);
        System.out.println(str);
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);

        var list1 = List.of(1, 2, 3);
        for (var n : list1) {
            System.out.print(n + " ");
        }

        // 以下写法会报错
        // 编译不通过，不能声明为null
        // var count = null;
        // 编译不通过,不能声明为Lambda表达式
        // var r = () -> Math.random();
        // 编译不通过,不能声明数组
        // var array = {1, 2, 3};
    }
}
