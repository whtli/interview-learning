package org.example.ch01_java.ch07_feature.java9.optional;

import java.util.Optional;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: Optional增强
 */
public class EnhancedOptionalTest {
    public static void main(String[] args) {
        // 两个参数Consumer和Runnable，如果Optional不为空调用Consumer，为空则调用Runnable
        Optional<Object> objectOptional1 = Optional.empty();
        objectOptional1.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Optional不为空调用Consumer，为空则调用Runnable")
        );

        // 一个Supplier参数，如果Optional为空则返回Supplier参数指定的Optional值
        Optional<Object> objectOptional2 = Optional.empty();
        objectOptional2.or(() -> Optional.of(
                "Optional为空则，返回Supplier参数指定的Optional值")
        ).ifPresent(System.out::println);
    }
}
