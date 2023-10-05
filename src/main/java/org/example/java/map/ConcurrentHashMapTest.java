package org.example.java.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: whtli
 * @date: 2023/10/05
 * @description: ConcurrentHashMap相关
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        // 创建一个CopyOnWriteArrayList实例
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // 向列表中添加元素
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("初始列表：" + list);

        // 使用get方法获取指定位置的元素
        System.out.println("列表第二个元素为：" + list.get(1));

        // 使用remove方法删除指定元素
        boolean result = list.remove("C++");
        System.out.println("删除结果：" + result);
        System.out.println("列表删除元素后为：" + list);

        // 使用set方法更新指定位置的元素
        list.set(1, "Golang");
        System.out.println("列表更新后为：" + list);

        // 使用add方法在指定位置插入元素
        list.add(0, "PHP");
        System.out.println("列表插入元素后为：" + list);

        // 使用size方法获取列表大小
        System.out.println("列表大小为：" + list.size());

        // 使用removeAll方法删除指定集合中所有出现的元素
        result = list.removeAll(new ArrayList<>(Arrays.asList("PHP", "Golang")));
        System.out.println("批量删除结果：" + result);
        System.out.println("列表批量删除元素后为：" + list);

        // 使用clear方法清空列表中所有元素
        list.clear();
        System.out.println("列表清空后为：" + list);
    }
}
