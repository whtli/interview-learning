package org.example.ch01_java.ch01_basic.ch08_string_relation;

/**
 * @author: whtli
 * @date: 2023/09/13
 * @description: StringBuilder相关，非线程安全
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append(2L);
        stringBuilder.append('a');
        stringBuilder.append(true);
        stringBuilder.append(1.1f);
        stringBuilder.append(2.3d);
        stringBuilder.append("hello");
        System.out.println(stringBuilder);

        stringBuilder.deleteCharAt(1);
        System.out.println(stringBuilder);
        stringBuilder.delete(3, 6);
        System.out.println(stringBuilder);

        stringBuilder.insert(2, 'x');
        System.out.println(stringBuilder);

        System.out.println(stringBuilder.reverse());

        System.out.println(stringBuilder.indexOf("x"));
        System.out.println(stringBuilder.indexOf("1", 1));
        System.out.println(stringBuilder.lastIndexOf("1"));
        System.out.println(stringBuilder.lastIndexOf("1", 9));

        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder.capacity());
        stringBuilder.ensureCapacity(44);
        System.out.println(stringBuilder.capacity());
        stringBuilder.trimToSize();
        System.out.println(stringBuilder.capacity());
    }
}

