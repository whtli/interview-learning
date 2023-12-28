package org.example.ch01_java.ch08_method.p03_optional_with_caution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author: whtli
 * @date: 2023/12/28
 * @description: 谨慎返回optional
 */
public class GetMaxWithoutOptional {
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Empty Collection");
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> words1 = new ArrayList<>(3);
        words1.add("hello");
        words1.add("world");
        words1.add("xxx");
        String lastWordInLexicon1 = max(words1);
        System.out.println("max value of words1: " + lastWordInLexicon1);

        List<String> words2 = new ArrayList<>(3);
        String lastWordInLexicon2 = max(words2);
        System.out.println("max value of words2: " + lastWordInLexicon2);
    }
}
