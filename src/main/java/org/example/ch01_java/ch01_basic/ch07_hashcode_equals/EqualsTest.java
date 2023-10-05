package org.example.ch01_java.ch01_basic.ch07_hashcode_equals;

/**
 * @author: whtli
 * @date: 2023/09/05
 * @description: == 和 equals() 的区别
 */
public class EqualsTest {
    public static void main(String[] args) {
        // a 为一个引用
        String a = new String("ab");
        // b为另一个引用,对象的内容一样
        String b = new String("ab");
        // false
        System.out.println(a == b);
        // true
        System.out.println(a.equals(b));
        // 放在常量池中
        String aa = "ab";
        // 从常量池中查找
        String bb = "ab";
        // true
        System.out.println(aa == bb);
    }
}
