package org.example.java.basic.ch9_else.abstract_interface;

/**
 * @author: whtli
 * @date: 2023/09/11
 * @description: 接口和抽象类
 */
public interface InterfaceTest {

}

interface Interface1 {
    /**
     * 不可以有构造方法
     */
    // public Interface1() {}

    /**
     * 不可以有方法体
     */
    public int method1();
}

interface Interface2 {
    // 接口中只能定义常量，不能定义实例变量
    public static final int CONSTANT_VALUE = 100;
    // public int INSTANCE_VALUE;

}

interface Interface3<T> {
    public T method2();
}

/**
 * 多实现
 */
class InterfaceMultipleImplementation implements Interface1, Interface2, Interface3<String> {

    @Override
    public int method1() {
        return 0;
    }

    @Override
    public String method2() {
        String ans = "InterfaceMultipleImplementation";
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(InterfaceMultipleImplementation.CONSTANT_VALUE);
        InterfaceMultipleImplementation test = new InterfaceMultipleImplementation();
        test.method2();
    }
}