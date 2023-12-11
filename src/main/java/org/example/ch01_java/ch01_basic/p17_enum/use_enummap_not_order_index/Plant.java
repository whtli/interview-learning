package org.example.ch01_java.ch01_basic.p17_enum.use_enummap_not_order_index;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * @author: whtli
 * @date: 2023/12/11
 * @description: 最好不要使用序数来索引数组，而要使用EnumMap。如果是多维的可以嵌套使用EnumMap。
 */
public class Plant {
    enum LifeCycle {
        /*
         一年生
         */
        ANNUAL,
        /*
         多年生
         */
        PERENNIAL,
        /*
         两年生
         */
        BIENNIAL
    }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = new Plant[4];
        garden[0] = new Plant("fir", LifeCycle.ANNUAL);
        garden[1] = new Plant("sec", LifeCycle.PERENNIAL);
        garden[2] = new Plant("thr", LifeCycle.BIENNIAL);
        garden[3] = new Plant("fou", LifeCycle.ANNUAL);

        /*
         需要进行为未受检的转换
         打印时需要手工标注索引的输出
         需要正确使用int值，但是int不能提供枚举的类型安全，如果使用了错误的值，程序会在程序员不知情的情况下完成错误的工作
         */
        System.out.println("------------使用序数索引------------");
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }
        for (Plant p : garden) {
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        }
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s, %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }

        /*
         使用EnumMap更简短、更清楚、更安全
         运行速度方面可以与使用序数的程序媲美
         没有不安全的转换
         不需要手工标注索引的输出
         集’Map丰富的功能和类型安全‘与’数组的快速‘于一身
         提供了运行时的泛型信息
         */
        System.out.println("\n------------使用EnumMap------------");
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle2 = new EnumMap<LifeCycle, Set<Plant>>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycle2.put(lc, new HashSet<>());
        }
        for (Plant p : garden) {
            plantsByLifeCycle2.get(p.lifeCycle).add(p);
        }
        System.out.println(plantsByLifeCycle2);

        /*
         基于stream的代码版本与EnumMap版本的稍有不同
         EnumMap版本总是给每一个Plant LifeCycle设计一个嵌套映射，基于stream的版本则仅当garden中包含了一种或多种Plant带有该LifeCycle时才会设计一个嵌套循环
         */
        System.out.println("\n------------使用stream------------");
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle)));
        // 在大量使用映射的程序中，这种使用有三种参数形式的Collectors.groupingBy方法，允许调用者利用map.Factory参数定义映射实现，是更优的选择，这与EnumMap版本的空间及时间性能吻合
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet())));
    }
}
