package org.example.ch01_java.ch07_feature.java11.string;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: 增加了字符串相关的操作方法
 */
public class EnhancedStringTest {
    public static void main(String[] args) {
        // 判空（纯空格也是空）
        String str0 = "  ";
        System.out.println("字符串str0的原始长度是：" + str0.length());
        System.out.println("判断字符串str0是否为空：" + str0.isBlank());

        // 去空格
        String str1 = " hello  world ";
        System.out.println("str1去掉首尾空格：" + str1.strip());
        System.out.println("str1去掉首部空格：" + str1.stripLeading());
        System.out.println("str1去掉尾部空格：" + str1.stripTrailing());

        // 重复字符串
        String str2 = "whtli";
        System.out.println("str2重复三次后：" + str2.repeat(3));

        // 返回由行终止符分隔的字符串集合
        String str3 = "sA\nBr\nCxx";
        long count = str3.lines().count();
        System.out.println("由行终止符分隔的字符串个数为：" + count);
        List<String> collect = str3.lines().collect(Collectors.toList());
        System.out.println(collect);
    }
}
