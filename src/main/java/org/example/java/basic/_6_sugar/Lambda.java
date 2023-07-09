package org.example.java.basic._6_sugar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: Lambda 表达式
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("whtli");
        strList.add("公众号:whtli");
        strList.add("博客：www.hollischuang.com");

        strList.forEach(System.out::println);

        List whtliList = strList.stream().filter(string -> string.contains("whtli")).collect(Collectors.toList());
        whtliList.forEach(System.out::println);

    }
}
