package org.example.ch01_java.ch07_feature.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: whtli
 * @date: 2023/10/16
 * @description: Stream相关
 * 链式编程，函数式接口类型，Stream不保存数据，不改变数据源
 * 一个Stream只能操作一次，操作完就关闭了，继续使用这个stream会报错
 */

public class StreamTest {
    public static void main(String[] args) {
        System.out.println("--------------------Stream相关--------------------");
        StreamTest streamTest = new StreamTest();
        streamTest.basic();
        streamTest.statistic();
        streamTest.other();
        System.out.println("--------------------------------------------------");
    }

    public void basic() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 过滤计数
        Stream<String> stringStream = strings.stream().filter(s -> s.contains("ab"));
        long count = stringStream.count();
        System.out.println("包含字符串ab的元素个数：" + count);

        System.out.println("遍历列表：");
        strings.stream().forEach(System.out::println);

        System.out.print("取指定数量元素并为转数组：");
        Stream<String> limit = strings.stream().limit(2);
        String[] array = limit.toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        System.out.println("对每个元素进行操作返回新stream：");
        Stream<String> map = strings.stream().map(s -> s + "16");
        map.forEach(System.out::println);

        System.out.println("借助stream排序：");
        strings.stream().sorted().forEach(System.out::println);

        System.out.print("collect把指定字符串（如，abc）放入容器中：");
        List<String> abc = strings.stream().filter(s -> s.equals("abc")).collect(Collectors.toList());
        System.out.println(abc);

        System.out.print("把list转为String，并添加逗号分隔符：");
        String collect = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(collect);

        System.out.println("合并流：");
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        long count1 = Stream.concat(strings2.stream(), strings.stream()).count();
        System.out.println("strings1的数量：" + strings.size());
        System.out.println("strings2的数量：" + strings2.size());
        System.out.println("合并流后的数量：" + count1);
        System.out.println();
    }

    public void statistic() {
        System.out.println("Stream用于统计：");
        List<Integer> number = Arrays.asList(1, 2, 5, 4);
        IntSummaryStatistics statistics = number.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("    列表中最大的数 : " + statistics.getMax());
        System.out.println("    列表中最小的数 : " + statistics.getMin());
        System.out.println("    列表中元素之和 : " + statistics.getSum());
        System.out.println("    列表中元素数量 : " + statistics.getCount());
        System.out.println("    列表元素平均值 : " + statistics.getAverage());
    }

    public void other() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 一个Stream只能操作一次，不能断开，否则会报错
        Stream stream = strings.stream();
        // 第一次使用
        // stream.limit(2);
        // 第二次使用
        // stream.forEach(System.out::println);
        // 报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        // 但是可以连续使用
        stream.limit(2).forEach(System.out::println);

        System.out.println("使用flatMap：");
        List<String[]> listOfArrays = Arrays.asList(
                new String[]{"apple", "banana", "cherry"},
                new String[]{"orange", "grape", "pear"},
                new String[]{"kiwi", "melon", "pineapple"}
        );
        List<String> flatMapResult = listOfArrays.stream()
                .flatMap(array -> Arrays.stream(array).map(String::toUpperCase))
                .collect(Collectors.toList());
        System.out.println(flatMapResult);
        System.out.println();
    }
}
