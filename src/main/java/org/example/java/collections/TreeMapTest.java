package org.example.java.collections;

import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: TreeMap相关
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Person, String> treeMap = new TreeMap<>();
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
class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

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
    }
}

