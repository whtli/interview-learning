package org.example.ch01_java.ch04_concurrent.lock;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: whtli
 * @date: 2023/04/02
 * @description:
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();
        list.add(1);
        list.add(2);
        System.out.println(list.get(1));
    }
}
