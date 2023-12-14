package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.special;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: Program containing annotations with a parameter
 */
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
    }

    // 对应版本2
    /*@ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doubleBad() {
        List<String> list = new ArrayList<>();
        // 规范允许此方法抛出IndexOutOfBoundsException或NullPointerException
        list.add(5, null);
    }*/

    /**
     * 对应版本3
     */
    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doubleBad() {
        List<String> list = new ArrayList<>();
        // 规范允许此方法抛出IndexOutOfBoundsException或NullPointerException
        list.add(5, null);
    }
}
