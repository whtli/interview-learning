package org.example.ch01_java.ch07_feature.java11.optional;

import java.util.Optional;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 新增了isEmpty()方法来判断指定的Optional对象是否为空
 */
public class EnhancedOptionalTest {
    public static void main(String[] args) {
        var op = Optional.empty();
        System.out.println(op.isEmpty());//判断指定的 Optional 对象是否为空
    }
}
