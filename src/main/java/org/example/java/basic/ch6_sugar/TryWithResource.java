package org.example.java.basic.ch6_sugar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: try-with-resource
 */
public class TryWithResource {
    public static void main(String... args) {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\WorkSpace\\IDEA\\interview-learning\\pom.xml"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // handle exception
        }
    }
}
