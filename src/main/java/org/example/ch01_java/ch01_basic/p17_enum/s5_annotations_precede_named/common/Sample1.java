package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.common;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: Program containing marker annotations
 */
public class Sample1 {
    @Test
    public static void m1() {
    }

    public static void m2() {
    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test
    public void m5() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }
}
