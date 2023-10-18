package org.example.ch01_java.ch07_feature.java8.optional;

import lombok.Data;

import java.util.*;

/**
 * @author: whtli
 * @date: 2023/10/16
 * @description: Optional用于解决NPE问题
 */

public class OptionalTest {
    public static void main(String[] args) {
        System.out.println("--------------------Optional相关--------------------");
        OptionalTest optionalTest = new OptionalTest();
        optionalTest.basic();
        System.out.println("--------------------------------------------------");
    }

    public void basic() {
        Zoo zoo = new Zoo();
        // 不会报错NPE，无输出
        Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).ifPresent(age -> System.out.println(age));
        // 直接调用报错NPE，因为没有初始化：java.lang.NullPointerException
        // System.out.println(zoo.getDog().getAge());
    }

    @Data
    class Zoo {
        private Dog dog;
    }

    @Data
    class Dog {
        private int age;
    }
}
