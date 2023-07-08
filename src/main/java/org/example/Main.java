package org.example;

/**
 * @author: whtli
 * @date: ${YEAR}/${MONTH}/${DAY}
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        StringBuilder path = new StringBuilder();
        path.append("hello");
//        path.delete(path.length() - 2, path.length());
        path.deleteCharAt(path.length() - 1);
        System.out.println(path);
        String test = "hello";
        test = test.substring(0, test.length() - 1);
        System.out.println(test);
    }
}