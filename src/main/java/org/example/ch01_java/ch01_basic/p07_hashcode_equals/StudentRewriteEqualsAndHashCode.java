package org.example.ch01_java.ch01_basic.p07_hashcode_equals;

import java.util.*;

import static java.util.Objects.hash;

/**
 * @author: whtli
 * @date: 2023/08/26
 * @description: 实现一个Student类，Student对象包括id，name，class_id
 * 任务:去重(三个字段全一样)，按class_id放在一起(存map里)
 * 关键点:重写equals()和hashCode()
 */

class Student {
    private int id;
    private String name;
    private int classId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }

    public Student(int id, String name, int classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Student sdu = (Student) obj;
        return id == sdu.id && classId == sdu.classId && Objects.equals(name, sdu.name);
    }

    @Override
    public int hashCode() {
        return hash(id, name, classId);
    }

    @Override
    public String toString() {
        return "Student {" + "id=" + id + ", name=" + name + ", classId=" + classId + "}";
    }
}

public class StudentRewriteEqualsAndHashCode {
    public static void main(String[] args) {
        Student sdu1 = new Student(1, "zhangsan", 1);
        Student sdu2 = new Student(2, "lisi", 2);
        Student sdu3 = new Student(2, "lisi", 2);
        Student sdu4 = new Student(4, "zhaoliu", 3);
        Student sdu5 = new Student(5, "sunqi", 3);
        System.out.println(sdu2.hashCode());
        System.out.println(sdu3.hashCode());
        System.out.println(sdu2.hashCode() == sdu3.hashCode());
        System.out.println(sdu2.equals(sdu3));
        System.out.println("------------------------------------------");

        List<Student> list = Arrays.asList(sdu1, sdu2, sdu3, sdu4, sdu5);
        Map<Integer, List<Student>> map = new HashMap<>();
        Map<Student, Integer> map2 = new HashMap<>();
        for (Student sdu : list) {
            map.putIfAbsent(sdu.getClassId(), new ArrayList<>());
            List<Student> students = map.get(sdu.getClassId());
            if (!students.contains(sdu)) {
                students.add(sdu);
            }
        }

        for (Map.Entry<Integer, List<Student>> entry : map.entrySet()) {
            System.out.println("class id : " + entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();
        }
        System.out.println("------------------------------------------");

        for (Student sdu : list) {
            map2.putIfAbsent(sdu, sdu.getId());
        }
        List<Map.Entry<Student, Integer>> list2 = new ArrayList<>(map2.entrySet());
        list2.sort((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Student, Integer> entry : list2) {
            System.out.println("student id : " + entry.getValue());
            System.out.println(entry.getKey());
            System.out.println();
        }
    }
}