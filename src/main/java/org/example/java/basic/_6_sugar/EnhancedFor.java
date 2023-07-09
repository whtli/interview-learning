package org.example.java.basic._6_sugar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 增强for循环
 */
public class EnhancedFor {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("whtli");
        strList.add("公众号:whtli");
        strList.add("博客：www.hexo.cn");
        strList.add("博客");

        // 会抛出ConcurrentModificationException异常
        // Iterator 在工作的时候是不允许被迭代的对象被改变的
        // 可以使用 Iterator 本身的方法remove()来删除对象
        // Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性
//        for (String stu : strList) {
//            if (stu.contains("whtli"))
//                strList.remove(stu);
//        }

        // 使用Iterator.remove()方法
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()) {
            String stu = iterator.next();
            if (stu.contains("whtli")) {
                iterator.remove();
            }
        }
        strList.forEach(System.out::println);

        // lambda表达式
        strList.removeIf(stu -> stu.contains("cn"));
        strList.forEach(System.out::println);
    }
}
