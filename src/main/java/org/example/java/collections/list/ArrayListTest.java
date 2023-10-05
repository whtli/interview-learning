package org.example.java.collections.list;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: ArrayList相关
 */
public class ArrayListTest {
    public static void main(String[] args) {
        // ArrayList和Array（数组）的区别
        compareArrayListAndArray();
        // ArrayList中添加null
        addNullToArrayList();
        // 遍历列表过程中删除指定元素
        deleteElementWhileTraversing();
        // ArrayList的clone能力
        cloneAbilityOfArrayList();
        // 排序
        compareAbility();
        // 与数组之间的转换
        transferAbility();
        // 其他功能
        elseAbilities();
    }


    /**
     * ArrayList和Array（数组）的区别
     */
    private static void compareArrayListAndArray() {
        // 初始化一个String类型的列表
        List<String> arrayList = new ArrayList<>(Arrays.asList("hello", "world", "!"));
        System.out.println(arrayList);
        // 修改指定下标的元素值
        arrayList.set(0, "Hello");
        System.out.println(arrayList);
        // 删除列表中的元素，调用api，无需手动移动元素
        arrayList.remove(arrayList.size() - 1);
        System.out.println(arrayList);
        // 动态地向列表中添加元素，调用api，无需手动移动元素
        arrayList.add(arrayList.size() - 1, "whtli");
        System.out.println(arrayList);
        System.out.println();

        // 初始化一个String类型的数组
        String[] array = {"hello", "world", "!"};
        System.out.println(Arrays.toString(array));
        // 修改指定下标的元素值
        array[0] = "Hello";
        System.out.println(Arrays.toString(array));
        // 删除数组中的元素，需要手动前移后续元素
        for (int idx = 0; idx < array.length - 2; idx++) {
            array[idx] = array[idx + 1];
        }
        for (int idx = array.length - 2; idx < array.length; idx++) {
            array[idx] = null;
        }
        System.out.println(Arrays.toString(array));
        // 向数组中新增元素，需要手动后移后续元素，且舍弃末尾元素
        for (int idx = array.length - 1; idx > array.length - 2; idx--) {
            array[idx] = array[idx - 1];
        }
        array[array.length - 2] = "whtli";
        System.out.println(Arrays.toString(array));
        System.out.println();
    }

    /**
     * ArrayList中添加null
     */
    private static void addNullToArrayList() {
        // ArrayList中可以存储任何类型的对象，包括null
        // 但是不建议向ArrayList中添加null， null无意义，会让代码难以维护比如忘记做判空处理就会导致NPE
        List<String> listContainsNull = new ArrayList<>();
        listContainsNull.add(null);
        listContainsNull.add("'null' can be insert into a list as value");
        System.out.println(listContainsNull);
        System.out.println();
    }

    /**
     * 遍历列表过程中删除指定元素
     */
    private static void deleteElementWhileTraversing() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 3));
        System.out.println("original list : " + list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int item = iterator.next();
            if (item == 3) {
                iterator.remove();
            }
        }
        System.out.println("after delete 3 by iterator : " + list);

        list.removeIf(x -> x == 4);
        System.out.println("after delete 4 by lambda   : " + list);
    }

    /**
     * ArrayList的clone能力
     */
    private static void cloneAbilityOfArrayList() {
        ArrayList<String> oriArrayList = new ArrayList<>(Arrays.asList("hello", "world", "!"));
        ArrayList<String> newArrayList = (ArrayList<String>) oriArrayList.clone();
        System.out.println(oriArrayList);
        System.out.println(newArrayList);
        // 内容相同，返回true（本质还是浅拷贝，如果列表的元素类型限定是某个类，那么这个浅拷贝的不同会更明显）
        System.out.println(oriArrayList.equals(newArrayList));
        // 实际地址不同，返回false
        System.out.println(oriArrayList == newArrayList);
        System.out.println();
    }

    /**
     * 比较
     */
    public static void compareAbility() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始列表: " + arrayList);

        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("默认排序: " + arrayList);
        // 定制排序(此处实现为逆序)
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序: " + arrayList);
        System.out.println();
    }

    /**
     * 其他功能
     */
    private static void elseAbilities() {
        // 创建一个初始容量为 10 的 ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        // 向 ArrayList 中添加一些元素
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        // debug可以看到列表容量的变化
        // 使用 trimToSize 方法将容量调整为与大小相等
        arrayList.trimToSize();
        // 扩容机制，增加此ArrayList实例的容量，以确保它至少能容纳元素的数量
        arrayList.ensureCapacity(15);


        ArrayList<Object> list1 = new ArrayList<Object>();
        final int N1 = 10000000;
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < N1; i++) {
            list1.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前：" + (endTime1 - startTime1));

        ArrayList<Object> list2 = new ArrayList<Object>();
        final int N2 = 10000000;
        long startTime2 = System.currentTimeMillis();
        list2.ensureCapacity(N2);
        for (int i = 0; i < N2; i++) {
            list2.add(i);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后：" + (endTime2 - startTime2));
    }


    /**
     * 与数组之间的转换
     */
    private static void transferAbility() {
        // 数组转集合
        String[] str = {"dog", "cat", "fish"};
        List<String> list = new ArrayList<>(Arrays.asList(str));
        System.out.println(list);
        // 集合转数组
        Collections.reverse(list);
        str = list.toArray(new String[0]);
        System.out.println(Arrays.toString(str));

        // Arrays.asList()是泛型方法，传递的数组必须是对象数组，而不是基本类型
        List myList = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(myList.size()); // 预期长度是3，实际是1
        System.out.println(myList); // 会打印数组地址
        int[] array = (int[]) myList.get(0); // 是数组
        System.out.println(array[0]);

        // 包装类可以使数组真正转为预期长度的List
        List myList1 = Arrays.asList(new Integer[]{11, 22, 33});
        System.out.println(myList1.size());
        System.out.println(myList1);
        int elem = (int) myList1.get(0);
        System.out.println(elem);

        // 直接使用Arrays.asList转换也不合理
        // 错误写法
        List<Integer> myList2 = Arrays.asList(1, 2, 3);
        System.out.println(myList2);
        System.out.println(myList.getClass()); // class java.util.Arrays$ArrayList
        // 正确写法
        List<Integer> myList3 = new ArrayList<>(Arrays.asList(7, 8, 9));
        System.out.println(myList3);
        System.out.println(myList3.getClass()); // class java.util.ArrayList

        List<Integer> myList4 = Arrays.stream(new Integer[]{1, 2, 3}).collect(Collectors.toList());
        System.out.println(myList4.getClass()); // class java.util.ArrayList

        List<Integer> myList5 = Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList()); // 依赖boxed装箱操作，基本类型也可以实现转换
        System.out.println(myList5.getClass()); // class java.util.ArrayList
        myList5.add(4);
        System.out.println(myList5);
    }
}
