package org.example.java.map;

import lombok.Data;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: TreeMap相关
 */
public class TreeMapTest {
    public static void main(String[] args) {
        // 相比于HashMap，TreeMap主要多了对集合中的元素根据键排序的能力以及对集合内元素的搜索的能力
        TreeMap<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o2.getName().compareTo(o1.getName()), 0);
            }
        });
        treeMap.put(new Person("zhangsan", 30), "张三");
        treeMap.put(new Person("lisi", 20), "李四");
        treeMap.put(new Person("wangwu", 10), "王五");
        treeMap.put(new Person("xiaohong", 15), "小红");

        for (Map.Entry<Person, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey().getAge() + "-" + entry.getKey().getName() + "-" + entry.getValue());
        }
    }
}

@Data
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

/*  如果重写compareTo，而不是在定义TreeMap的时候new Comparator，需要让Person类实现Comparable接口：implements Comparable<Person>
    @Override
    public int compareTo(Person o) {
        // 简写版
        // return Integer.compare(name.compareTo(o.name), 0);

        // 手写版
        if (name.compareTo(o.name) > 0) {
            return 1;
        }
        if (name.compareTo(o.name) < 0) {
            return -1;
        }
        return 0;
    }*/
}

