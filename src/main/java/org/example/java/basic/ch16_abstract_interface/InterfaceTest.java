package org.example.java.basic.ch16_abstract_interface;

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
    /**
     * 接口中只能定义常量，不能定义实例变量
     * 在Java 9及更高版本中，可以省略 public static final 修饰符，变量会被自动视为常量
     * 例如以下两个，不管怎么简写，本质都是常量：
      */
    public static final int CONSTANT_VALUE = 100;
    public int CONSTANT_VALUE1 = 2100;
}

interface Interface3<T> {
    /**
     * 可以定义泛型接口
     */
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
        System.out.println(InterfaceMultipleImplementation.CONSTANT_VALUE1);
        InterfaceMultipleImplementation test = new InterfaceMultipleImplementation();
        test.method2();
    }
}