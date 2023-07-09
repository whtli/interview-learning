package org.example.java.basic._1_copy;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description:
 */
public class Person implements Cloneable {
    private Address address;

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    浅拷贝
//    @Override
//    public Person clone() {
//        try {
//            Person person = (Person) super.clone();
//            return person;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }

    // 深拷贝
    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            person.setAddress(person.getAddress().clone());
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
