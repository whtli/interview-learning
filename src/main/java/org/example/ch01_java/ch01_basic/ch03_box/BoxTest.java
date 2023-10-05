package org.example.ch01_java.ch01_basic.ch03_box;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: 拆箱和装箱
 * 装箱其实就是调用了 包装类的valueOf()方法，拆箱其实就是调用了 xxxValue()方法
 * 如果频繁拆装箱的话，也会严重影响系统的性能。应该尽量避免不必要的拆装箱操作
 */
public class BoxTest {
    public static void main(String[] args) {
        Integer i = 10;  //装箱,等价于 Integer i = Integer.valueOf(10)
        int n = i;   //拆箱,等价于 int n = i.intValue();
        System.out.println(i == n);
    }

    private static long sum() {
        // 应该使用 long 而不是 Long，因为会频繁装箱
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
}
