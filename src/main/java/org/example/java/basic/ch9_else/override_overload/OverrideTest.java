package org.example.java.basic.ch9_else.override_overload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * @author: whtli
 * @date: 2023/09/11
 * @description: 重载和重写
 */

/**
 * 重写，发生阶段：运行期
 */
class ParentClass {
    public String method1(String str) {
        System.out.println("hello:" + str);
        return str;
    }

    public Object method2(String str) {
        return str;
    }

    public void method3() throws IOException {

    }

    protected void method4(String str) {
        System.out.println("hello:" + str);
    }
}

/**
 * 重写
 */
public class OverrideTest extends ParentClass {

    /**
     * 两同：方法名和参数列表必须相同
     */
    @Override
    public String method1(String str) {
        System.out.println("重写:" + str);
        return str;
    }

    /**
     * 两小（1）：被子类重写的方法返回类型需要小于或等于父类方法的返回类型
     */
    @Override
    public String method2(String str) {
        return str;
    }

    /**
     * 两小（2）：被子类重写的方法异常类型需要小于或等于父类方法的异常类型
     * 子类在重写父类方法时，
     * 可以选择不抛出异常，
     * 或者抛出与父类方法声明的异常相同或更具体的异常。
     * 但是！子类不能抛出比父类方法声明的异常更宽泛的异常！
     */
    @Override
    public void method3() throws FileNotFoundException {

    }

    /**
     * 一大：子类的权限修饰符范围与父类相等或范围更大
     */
    @Override
    public void method4(String str) {
        super.method4(str);
        System.out.println("重写:" + str);
    }

    public static void main(String[] args) {
        ParentClass overrideTest = new OverrideTest();
        overrideTest.method1("method1");
        overrideTest.method4("method2");
    }
}
