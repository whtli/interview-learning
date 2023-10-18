package org.example.ch01_java.ch07_feature.java9.trywithresource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author: whtli
 * @date: 2023/10/18
 * @description: try-with-resources增强
 */
public class EnhancedTryWithResourceTest {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner1 = new Scanner(new File("test.txt"));
             PrintWriter writer1 = new PrintWriter(new File("test.txt"))) {
            // ……
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 在try-with-resources语句中可以使用effectively-final变量(没有被final修饰但是值在初始化后从未更改的变量)
        Scanner scanner2 = new Scanner(System.in);
        PrintWriter writer2 = new PrintWriter(new File("testWrite.txt"));
        try (scanner2; writer2) {
            // ……
        }
    }
}
