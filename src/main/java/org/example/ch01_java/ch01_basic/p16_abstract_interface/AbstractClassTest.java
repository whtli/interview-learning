package org.example.ch01_java.ch01_basic.p16_abstract_interface;

/**
 * @author: whtli
 * @date: 2023/09/11
 * @description: 接口和抽象类
 */
public abstract class AbstractClassTest {
    /**
     * 常量
     */
    public final static int CONSTANT_VALUE = 100;
    /**
     * 静态变量
     */
    public static int STATIC_VALUE = 101;
    /**
     * 实例变量
     */
    public int instanceValue;

    /**
     * 可以不带方法体
     * @return 整数
     */
    public abstract int method1();

    /**
     * 可以带方法体
     * @return 整数
     */
    public int method2() {
        return 0;
    }
}

/**
 * 只能单继承
 */
class AbstractClassChild extends AbstractClassTest {
    public AbstractClassChild() {
    }

    public AbstractClassChild(int val) {
        this.instanceValue = val;
    }

    /**
     * 子类对抽象类中方法的实现
     */
    @Override
    public int method1() {
        return 10010;
    }

    /**
     * 子类对抽象类中方法的实现
     */
    @Override
    public int method2() {
        // return super.method2();
        return 10011;
    }
}

class AbstractInstance {
    public static void main(String[] args) {
        System.out.println(AbstractClassChild.CONSTANT_VALUE);
        System.out.println(AbstractClassChild.STATIC_VALUE);

        AbstractClassChild abstractClassChild1 = new AbstractClassChild();
        AbstractClassChild abstractClassChild2 = new AbstractClassChild(103);
        System.out.println(abstractClassChild1.instanceValue);
        System.out.println(abstractClassChild2.instanceValue);
    }
}
