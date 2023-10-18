package org.example.ch01_java.ch07_feature.java8.lambda;

import java.util.*;

/**
 * @author: whtli
 * @date: 2023/10/16
 * @description: Lambda相关
 */

public class LambdaTest {
    public static void main(String[] args) {
        System.out.println("--------------------Lambda相关--------------------");
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.comparator();
        lambdaTest.collections();
        try {
            lambdaTest.runnable();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------");
    }

    public void comparator() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 4, 1, 3));
        System.out.println("原始数据：" + list);

        System.out.println("传统的借助Comparator进行列表排序的方式：");
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);

        System.out.println("使用lambda借助Comparator进行列表排序的方式：");
        list.sort((o1, o2) -> o1 - o2);
        System.out.println(list);

        Collections.sort(list, (o1, o2) -> o2 - o1);
        System.out.println(list);

        System.out.println();

        List<String> strings = new ArrayList<>(Arrays.asList("hello", "world", "java", "v8"));
        System.out.println("原始的字符串列表0：" + strings);

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        System.out.println("排序后字符串列表1：" + strings);

        Collections.sort(strings, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
        System.out.println("排序后字符串列表2：" + strings);

        Collections.sort(strings, Comparator.comparingInt(String::length));
        System.out.println("排序后字符串列表3：" + strings);

        strings.sort(Comparator.comparingInt(String::length));
        System.out.println("排序后字符串列表4：" + strings);

        System.out.println();
    }

    public void collections() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 4, 1, 3));

        System.out.print("传统的遍历列表方式：");
        for (Integer item : list) {
            System.out.print(item);
        }
        System.out.println();

        System.out.print("使用lambda遍历列表，方式一：");
        list.forEach(s -> System.out.print(s));
        System.out.println();

        System.out.print("使用lambda遍历列表，方式二：");
        list.forEach(System.out::print);
        System.out.println();

        System.out.println("类似的，使用lambda遍历map：");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "wht");
        map.put(2, "li");
        map.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println();
    }

    public void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("传统的借助Runnable创建线程的方式");
            }
        }).start();

        new Thread(() -> System.out.println("使用lambda借助Runnable创建线程的方式")).start();
    }
}