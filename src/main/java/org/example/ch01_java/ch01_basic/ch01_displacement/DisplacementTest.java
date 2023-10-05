package org.example.ch01_java.ch01_basic.ch01_displacement;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: 移位运算符
 * Java中有三种移位运算符
 * << : 左移，向左移若干位，高位丢弃，低位补零。不溢出的情况下，x << 1,相当于 x 乘以 2
 * >> : 带符号右移，向右移若干位，高位补符号位（正数高位补 0,负数高位补 1），低位丢弃。x >> 1,相当于 x 除以 2
 * >>> : 无符号右移，向右移若干位，忽略符号位，空位都以 0 补齐。
 *
 * double，float不能来进行移位操作
 * 移位操作符实际上支持的类型只有int和long，编译器在对short、byte、char类型进行移位前，都会将其转换为int类型再操作
 *
 * 当 int 类型左移/右移位数大于等于 32 位操作时，会先求余（%）后再进行左移/右移操作。。
 * 当 long 类型进行左移/右移操作时，由于 long 对应的二进制是 64 位，因此求余操作的基数也变成了 64。
 * x<<42等同于x<<10，x>>42等同于x>>10，x>>>42等同于x>>>10
 */
public class DisplacementTest {
    public static void main(String[] args) {
        // 左移
        int left1 = -1;
        System.out.println("初始数据：" + left1);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(left1));
        left1 <<= 10;
        System.out.println("左移 10 位后的数据 " + left1);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(left1));
        System.out.println();

        int left2 = -1;
        System.out.println("初始数据：" + left2);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(left2));
        // 左移 42 位相当于左移 10 位（42%32=10）
        left2 <<= 42;
        System.out.println("左移 10 位后的数据 " + left2);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(left2));
        System.out.println();

        int left3 = 1;
        System.out.println("初始数据：" + left3);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(left3));
        left3 <<= 1;
        System.out.println("左移 10 位后的数据 " + left3);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(left3));


        // 带符号右移
        int right1 = -1;
        System.out.println("初始数据：" + right1);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(right1));
        right1 >>= 10;
        System.out.println("带符号右移 10 位后的数据 " + right1);
        System.out.println("带符号右移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(right1));
        System.out.println();


        // 无符号右移，高位直接补0
        int right2 = -1;
        System.out.println("初始数据：" + right2);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(right2));
        right2 >>>= 10;
        System.out.println("无符号右移 10 位后的数据 " + right2);
        System.out.println("无符号右移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(right2));
        System.out.println();
    }
}
