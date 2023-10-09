package org.example.ch01_java.ch04_concurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicReference功能测试
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("SnailClimb", 22);
        ar.set(person);
        System.out.println(ar);

        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(person, updatePerson);
        System.out.println(ar);
    }
}
@Data
@AllArgsConstructor
class Person {
    private String name;
    private int age;
}