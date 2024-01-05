package org.example.ch01_java.ch09_common;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * @author: whtli
 * @date: 2024/01/05
 * @description: for-each循环优先于传统的for循环
 */
public class ForEachBetterThanFor2 {
    public static void main(String[] args) {
        Collection<Face> faces = EnumSet.allOf(Face.class);
        int cnt1 = 0;
        for (Iterator<Face> i = faces.iterator(); i.hasNext(); ) {
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); ) {
                // 不会抛出异常，而只是打印6个重复的词，而不是预计的36种组合
                System.out.println(i.next() + " " + j.next());
                cnt1++;
            }
        }

        System.out.println(cnt1);
        System.out.println("---------------");

        int cnt2 = 0;
        for (Iterator<Face> i = faces.iterator(); i.hasNext(); ) {
            // 为了修正上述示例中的bug，必须在外部循环的作用域中添加一个变量来保存外部元素
            Face face = i.next();
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); ) {
                System.out.println(face + " " + j.next());
                cnt2++;
            }
        }
        System.out.println(cnt2);
    }
}

enum Face {
    ONE, TWO, THREE, FOUR, FIVE, SIX
}