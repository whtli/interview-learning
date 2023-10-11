package org.example.ch01_java.ch01_basic.p07_hashcode_equals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: whtli
 * @date: 2023/04/01
 * @description:
 */
public class HashCodeAndEquals {
    public static void main(String[] args) {
        Map<User, String> map = new HashMap<>();
        map.put(new User("hello"), "123");
        /**
         * source code：
         * if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
         *     return e;
         * 先判断hash是否相同，所以重写equals()判断相等，需要重写hashCode()
         *
         * 原理：
         * 两个对象相同，则这两个对象的hashcode一定相同
         * 两个对象的hashcode不相同，则这两个对象一定不相同（此处使用的原理）
         *
         * 但是两个对象的hashcode相同，这两个对象不一定相同（哈希冲突）
         *
         */
        System.out.println(map.get(new User("hello")));
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
