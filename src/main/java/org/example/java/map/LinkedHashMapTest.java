package org.example.java.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: whtli
 * @date: 2023/10/05
 * @description: LinkedHashMap相关
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        basicAbility();
        accessOrder();
        // 通过 LinkedHashMap 可以封装一个简易版的 LRU
        lruTest();

    }


    private static void basicAbility() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("l", 12);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("z", 26);
        // LinkedHashMap的迭代顺序和插入顺序一致
        System.out.println(map);
    }

    private static void accessOrder() {
        System.out.println("----------------访问顺序遍历----------------");
        // 访问顺序遍历
        // 使用传入accessOrder属性为true的构造方法，表示其具备访问有序性
        Map<String, Integer> map1 = new LinkedHashMap<>(16, 0.75f, true);
        map1.put("l", 12);
        map1.put("a", 1);
        map1.put("b", 2);
        map1.put("z", 26);
        System.out.println(map1);
        // 被访问的元素会被移动至链表末端
        map1.get("a");
        map1.get("l");
        System.out.println(map1);
    }

    private static void lruTest() {
        System.out.println("----------------LRU TEST----------------");
        LRUCache<Integer, String> lruCache = new LRUCache<>(4);
        lruCache.put(1, "a");
        lruCache.put(2, "b");
        lruCache.put(3, "c");
        System.out.println(lruCache);
        lruCache.put(4, "d");
        System.out.println(lruCache);
        lruCache.put(5, "e");
        System.out.println(lruCache);
        for (int i = 0; i <= 5; i++) {
            System.out.println(lruCache.get(i));
        }

    }
}


class LRUCache<K, V> extends LinkedHashMap<K, V> {
    public int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}