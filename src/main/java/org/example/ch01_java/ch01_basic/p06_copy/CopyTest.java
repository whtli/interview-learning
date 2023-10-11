package org.example.ch01_java.ch01_basic.p06_copy;

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

        // 在数组上调用clone返回的数组，其编译时的类型与被克隆数组的类型相同，不需要进行强制类型转换
        // 比如下方代码不需要写成  int[] now = (int[]) ori.clone()
        int[] ori = {1, 2, 3};
        int[] now = ori.clone();
        for (int i : ori) {
            System.out.print(i);
        }
        System.out.println();
        for (int i : now) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(ori);
        System.out.println(now);
        System.out.println(ori.equals(now));
    }
}

class Address implements Cloneable{
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
