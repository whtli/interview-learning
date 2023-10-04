package org.example.java.collections.set;

import lombok.Data;

import java.util.TreeSet;

/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: TreeSet相关
 */
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person("zhangsan", 30));
        treeSet.add(new Person("lisi", 20));
        treeSet.add(new Person("wangwu", 10));
        treeSet.add(new Person("xiaohong", 15));

        for (Person person : treeSet) {
            System.out.println(person.getAge() + "-" + person.getName());
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


