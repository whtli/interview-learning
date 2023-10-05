package org.example.java.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: whtli
 * @date: 2023/04/03
 * @description: HashMap相关
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
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
}
