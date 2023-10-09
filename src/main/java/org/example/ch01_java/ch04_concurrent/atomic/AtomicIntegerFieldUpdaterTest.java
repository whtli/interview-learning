package org.example.ch01_java.ch04_concurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicIntegerFieldUpdater功能测试
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        // 每次使用都必须使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User("Java", 22);
        System.out.println(updater.getAndIncrement(user));
        System.out.println(updater.get(user));
    }
}

@Data
@AllArgsConstructor
class User {
    private String name;
    // 需要被更新的对象属性必须使用public volatile修饰
    public volatile int age;
}