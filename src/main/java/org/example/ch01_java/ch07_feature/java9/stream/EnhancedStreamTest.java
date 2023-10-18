package org.example.ch01_java.ch07_feature.java9.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: Stream增强
 */
public class EnhancedStreamTest {
    public static void main(String[] args) {

        // 可以包含一个非空元素的Stream
        Stream<String> stringStream = Stream.ofNullable("Java");
        System.out.println(stringStream.count());

        // 可以创建一个空的Stream
        Stream<String> nullStream = Stream.ofNullable(null);
        System.out.println(nullStream.count());

        // takeWhile()可以从Stream中依次获取满足条件的元素，直到不满足条件时停止获取
        List<Integer> integerList = List.of(11, 33, 66, 8, 9, 13);
        integerList.stream().takeWhile(x -> x < 50).forEach(System.out::print);
        System.out.println();
        // dropWhile()和takeWhile()相反，从Stream中依次获取删除满足条件的元素，直到不满足条件时停止删除
        integerList.stream().dropWhile(x -> x < 40).forEach(System.out::print);
        System.out.println();

        // 使用原始iterate()方法输出数字1~10
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::print);
        System.out.println();
        // 使用新的iterate()重载方法输出数字1~10
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::print);
    }
}
