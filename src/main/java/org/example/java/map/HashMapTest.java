package org.example.java.map;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: whtli
 * @date: 2023/04/03
 * @description: HashMap相关
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        basicAbility(map);
        IterationAbility(map);
        transAbility();
        elseAbilities();
    }


    private static void basicAbility(Map<Integer, Integer> map) {
        System.out.println("map.size(): " + map.size());
        map.put(1, 2);
        map.put(2, 3);
        // 可以存储以null为key的键值对，但是只能存一个
        map.put(null, 4);
        map.put(null, 5);
        // 可以存储多个以null为value但不以null为key的键值对，可以存多个
        map.put(6, null);
        map.put(7, null);
        System.out.println("map.size(): " + map.size());
    }


    private static void IterationAbility(Map<Integer, Integer> map) {
        // 遍历方式
        System.out.println("\n-------1.迭代器 EntrySet-------");
        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator1.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // 2.迭代器 KeySet
        System.out.println("\n-------2.迭代器 KeySet-------");
        Iterator<Integer> iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer key = iterator2.next();
            System.out.println(key + " " + map.get(key));
        }
        // 3.ForEach EntrySet
        System.out.println("\n-------3.ForEach EntrySet-------");
        for (Map.Entry<Integer, Integer> next : map.entrySet()) {
            System.out.println(next.getKey() + " " + next.getValue());
        }
        // 4.ForEach KeySet
        System.out.println("\n-------4.ForEach KeySet-------");
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
        // 5.Lambda
        System.out.println("\n-------Lambda-------");
        map.forEach((key, value) -> System.out.println(key + " " + value));
        // 6.Streams API 单线程
        System.out.println("\n-------6.Streams API 单线程-------");
        map.entrySet().stream().forEach((entry) -> System.out.println(entry.getKey() + " " + entry.getValue()));
        // 7.Streams API 多线程
        System.out.println("\n-------7.Streams API 多线程-------");
        map.entrySet().parallelStream().forEach((entry) -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    private static void transAbility() {
        @Data
        @AllArgsConstructor
        class Person {
            private String name;
            private String phone;
        }
        List<Person> bookList = new ArrayList<>();
        bookList.add(new Person("zhangsan", "Effective Java"));
        bookList.add(new Person("lisi", "Core Java"));
        // 使用java.util.stream.Collectors类的toMap()方法将列表转为集合时，如果存在某个value为null，会抛NPE
        // 以1.8为例，toMap方法中调用了Map接口的merge方法，而merge方法会先调用Objects.requireNonNull()判断value是否为空
        // bookList.add(new Person("wangwu", null));
        Map<String, String> collect = bookList.stream().collect(Collectors.toMap(Person::getName, Person::getPhone));
        System.out.println(collect);
    }

    /**
     * HashMap其他常用方法
     */
    private static void elseAbilities() {
        Map<String, String> map = new HashMap<>();
        // 键不能重复，值可以重复
        map.put("san", "张三");
        map.put("si", "李四");
        map.put("wu", "王五");
        map.put("lao", "老王");
        map.put("lao", "老王1");
        System.out.println("-------直接输出hashmap:-------");
        System.out.println(map);
        System.out.println("map.size()：" + map.size());
        System.out.println("map.isEmpty()：" + map.isEmpty());
        System.out.println(map.remove("si"));
        System.out.println("map.remove()：" + map);
        System.out.println("map.get(si)：" + map.get("si"));
        System.out.println("map.containsKey(si)：" + map.containsKey("si"));
        System.out.println("containsValue(李四)：" + map.containsValue("李四"));
        System.out.println(map.replace("wu", "王五2"));
        System.out.println("map.replace(wu, 王五2):" + map);
    }
}
