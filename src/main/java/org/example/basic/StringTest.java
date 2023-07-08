package org.example.basic;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/04/09
 * @description:
 */
public class StringTest {
    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        String s = Arrays.toString(chars);
        System.out.println(s);

    }
}
