package org.example.basic;

/**
 * @author: whtli
 * @date: 2023/04/08
 * @description:
 */
public class Child extends Parent {
    public Child(String name, int age) {
        super(name, age);
    }
    @Override
    public void printInfo() {
        System.out.println(name + " is " + age + " now --- Child.");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
