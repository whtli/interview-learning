package org.example.ch01_java.ch01_basic.p15_sugar;

/**
 * @author: whtli
 * @date: 2023/10/02
 * @description: 数值字面量
 * 不管是整数还是浮点数，都允许在数字之间插入任意多个下划线
 * 这些下划线不会对字面量的数值产生影响，目的就是方便阅读
 * 反编译后就把_删除了，编译器并不认识在数字字面量中的 _ ，在编译阶段会把它们去掉
 */
public class Literal {
    public static void main(String[] args) {
        int val1 = 10_999;
        System.out.println(val1);

        double val2 = 10_012.333_333;
        System.out.println(val2);
    }
}
