package org.example.basic;

/**
 * @author: whtli
 * @date: 2023/04/08
 * @description:
 */
public class Parent {
    public String name;
    public int age;

    public Parent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printInfo() {
        System.out.println(this.name + " is " + this.age + " now.");
    }

    public void printInfo(int height) {
        System.out.println(this.name + " is " + this.age + " and " + height +  "cm now.");
    }

}
