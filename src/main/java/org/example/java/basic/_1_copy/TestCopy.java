package org.example.java.basic._1_copy;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description:
 */
public class TestCopy {
    public static void main(String[] args) {
        Person person1 = new Person(new Address("Beijing"));
        Person person2 = person1.clone();
        System.out.println(person1.getAddress() == person2.getAddress());
    }
}
