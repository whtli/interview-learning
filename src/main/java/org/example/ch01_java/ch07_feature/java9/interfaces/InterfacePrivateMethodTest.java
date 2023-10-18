package org.example.ch01_java.ch07_feature.java9.interfaces;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: Java9允许在接口中定义、使用私有方法，使接口的使用更加灵活
 */


interface InterfaceWithPrivateMethod {
    /**
     * 在接口中定义私有方法
     */
    private void privateMethod() {
        System.out.println("这是在接口中定义的私有方法");
    }

    /*
     * 只能在接口内部调用私有方法
     */
    default void defaultMethod() {
        System.out.println("在接口内部调用私有方法");
        privateMethod();
    }
}

public class InterfacePrivateMethodTest implements InterfaceWithPrivateMethod {
    /**
     * 通过重写的方式间接调用接口中的私有方法
     */
    @Override
    public void defaultMethod() {
        InterfaceWithPrivateMethod.super.defaultMethod();
    }

    public static void main(String[] args) {
        InterfaceWithPrivateMethod test = new InterfacePrivateMethodTest();
        test.defaultMethod();
    }
}