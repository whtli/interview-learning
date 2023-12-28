package org.example.ch01_java.ch08_method.p03_optional_with_caution;

import java.util.*;

/**
 * @author: whtli
 * @date: 2023/12/28
 * @description: 谨慎返回optional
 */
public class GetMaxWithOptional {
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        if (c.isEmpty()) {
            return Optional.empty();
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    public static <E extends Comparable<E>> Optional<E> max1(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }

    public static void main(String[] args) {
        List<String> words3 = new ArrayList<>(3);
        String lastWordInLexicon3 = max(words3).orElse("No world");
        System.out.println("max value of words3: " + lastWordInLexicon3);

        List<String> words5 = new ArrayList<>(3);
        String lastWordInLexicon5 = max1(words5).orElse("No world");
        System.out.println("max value of words3: " + lastWordInLexicon5);

        List<String> words4 = new ArrayList<>(3);
        String lastWordInLexicon4 = max(words4).orElseThrow(IllegalArgumentException::new);
        System.out.println("max value of words4: " + lastWordInLexicon4);

        List<String> words6 = new ArrayList<>(3);
        String lastWordInLexicon6 = max1(words6).orElseThrow(IllegalArgumentException::new);
        System.out.println("max value of words4: " + lastWordInLexicon6);
    }
}
