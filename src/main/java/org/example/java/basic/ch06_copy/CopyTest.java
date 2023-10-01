package org.example.java.basic.ch06_copy;

/**
 * @author: whtli
 * @date: 2023/07/08
 * @description: 深拷贝和浅拷贝
 */
public class CopyTest {
    public static void main(String[] args) {
        Person person1 = new Person(new Address("Beijing"));
        Person person2 = person1.clone();
        System.out.println(person1.getAddress() == person2.getAddress());
    }
}

class Address implements Cloneable {
    private String name;

    public Address(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

class Person implements Cloneable {
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
