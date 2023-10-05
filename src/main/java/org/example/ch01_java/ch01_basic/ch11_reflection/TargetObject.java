package org.example.ch01_java.ch01_basic.ch11_reflection;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description:
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        value = "interview-learning";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
