package org.example.java.basic.ch2_reflect;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description:
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
