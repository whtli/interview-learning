package org.example.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/04/03
 * @description:
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int item = iterator.next();
            if (item == 3) {
                iterator.remove();
            }
        }
        System.out.println(list);

        StringBuilder path1 = new StringBuilder();
        StringBuilder path2 = new StringBuilder();
        path1.toString().contains(path2.toString());

        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList);
        Integer[] myArray1 = {1, 2, 3};
        List myList1 = Arrays.asList(myArray1);
        System.out.println(myList1);
    }
}
